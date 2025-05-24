package com.ssafy.buyhouse.domain.member.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.estate.service.HouseService;
import com.ssafy.buyhouse.domain.mail.service.MailService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.domain.PwdQuestion;
import com.ssafy.buyhouse.domain.member.dto.reqeust.*;
import com.ssafy.buyhouse.domain.member.dto.response.ErrorResponse;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindIdResponse;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindPwdResponse;
import com.ssafy.buyhouse.domain.member.service.MemberService;
import com.ssafy.buyhouse.domain.member.service.PwdQuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {
    private final MemberService memberService;
    private final HouseService houseService;
    private final MailService mailService;
    private final PwdQuestionService pwdQuestionService;
    @GetMapping("/password")
    public ResponseEntity<?> getPwdQuestions(){
        List<PwdQuestion> pwdQuestionList = pwdQuestionService.findAll();
        return ResponseEntity.ok().body(pwdQuestionList);
    }

    //회원정보 조회 - 자산
/*    @GetMapping
    public ResponseEntity<MemberResponse> getMember(@LoginUser Member member){
        OwnedHouseListResponseDto ownedHouse = houseService.searchOwnedHouse(member.getId());
        return ResponseEntity.ok().body(MemberResponse.from(member));
    }*/

    //회원정보 수정
    @PutMapping
    public ResponseEntity<?> updateMember(@LoginUser Member member,
                                          @Valid @RequestBody MemberUpdateRequest memberUpdateRequest,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorResponse> errors = bindingResult.getFieldErrors().stream()
                    .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            checkUpdateDuplicated(member, memberUpdateRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        memberService.updateMember(memberUpdateRequest, member);
        return ResponseEntity.ok().build();
    }


    //회원 탈퇴
    @DeleteMapping
    public ResponseEntity<?> deleteMember(@LoginUser Member member){
        memberService.deleteByEntity(member);
        return ResponseEntity.ok().body(null);
    }

    //아이디 찾기
    @GetMapping("/recovery/id")
    public ResponseEntity<?> findUserId(@RequestBody MemberFindIdRequest memberFindIdRequest){
        Member member = memberService.findMemberId(memberFindIdRequest);
        //메일 전송
        String mail = mailService.findIdMail(new MemberFindIdResponse(member.getName(), member.getId()));
        mailService.sendMimeMessage(mail, "[집사] 아이디 찾기 결과입니다.", member.getEmail());
        return ResponseEntity.ok().body(null);
    }

    //비밀번호 찾기
    @GetMapping("/recovery/password")
    public ResponseEntity<?> findUserPwd(@RequestBody MemberFindPwdRequest memberFindPwdRequest){
        Member member = memberService.findMemberPwd(memberFindPwdRequest);
        //메일 전송 - 임시 비밀번호로 변경 후 임시 비밀번호 전송
        String tempPassword = String.valueOf(UUID.randomUUID()).substring(0,8);
        memberService.setMemberPwdTemp(tempPassword, member);
        String mail = mailService.findPwdMail(new MemberFindPwdResponse(member.getName(), tempPassword));
        mailService.sendMimeMessage(mail, "[집사] 비밀번호 찾기 결과입니다.", member.getEmail());
        return ResponseEntity.ok().body(null);
    }

    //비밀번호 변경
    @PutMapping("/password")
    public ResponseEntity<?> updatePwd(@LoginUser Member member, @RequestBody MemberUpdatePwdRequest memberUpdatePwdRequest){
        memberService.updateMemberPwd(memberUpdatePwdRequest, member);
        return ResponseEntity.ok().body(null);
    }

    //회원 랭킹 조회

    //전체 랭킹 조회

    // 회원가입
    @PostMapping
    public ResponseEntity<?> registerMember(@Valid @RequestBody MemberCreateRequest memberCreateRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorResponse> errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessage);
        }


        try {
            checkCreateDuplicated(memberCreateRequest);
            memberService.isPasswordSame(memberCreateRequest.password(), memberCreateRequest.passwordConfirm());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        memberService.registerMember(memberCreateRequest);
        return ResponseEntity.ok().build();
    }


    private void checkCreateDuplicated(MemberCreateRequest memberCreateRequest) {
        if(memberService.isIdDuplicated(memberCreateRequest.id())){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if(memberService.isEmailDuplicated(memberCreateRequest.email())){
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        if(memberService.isNameDuplicated(memberCreateRequest.name())){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

    private void checkUpdateDuplicated(Member member, MemberUpdateRequest memberUpdateRequest) {
        if(memberService.isEmailDuplicated(member, memberUpdateRequest.email())){
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        if(memberService.isNameDuplicated(member, memberUpdateRequest.name())){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }


}
