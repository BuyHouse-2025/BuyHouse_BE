package com.ssafy.buyhouse.domain.member.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.dto.reqeust.*;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindIdResponse;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindPwdResponse;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import com.ssafy.buyhouse.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원정보 수정
    @PutMapping
    public ResponseEntity<?> updateMember(@LoginUser Member member, @RequestBody MemberUpdateRequest memberUpdateRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

        }

        try{
            checkUpdateDuplicated(memberUpdateRequest);
        }
        catch (IllegalArgumentException e){

        }

        memberService.updateMember(memberUpdateRequest, member);

        return ResponseEntity.ok().body(null);
    }

    //회원 탈퇴
    @DeleteMapping
    public ResponseEntity<?> deleteMember(@LoginUser Member member){
        memberRepository.delete(member);
        return ResponseEntity.ok().body(null);
    }

 /*   //아이디 찾기
    @GetMapping("/recovery/id")
    public ResponseEntity<?> findUserId(@RequestBody MemberFindIdRequest memberFindIdRequest){
        MemberFindIdResponse memberId = memberService.findMemberId(memberFindIdRequest);
        //메일 전송
        return ResponseEntity.ok().body(memberId);
    }

    //비밀번호 찾기
    @GetMapping("/recovery/password")
    public ResponseEntity<?> findUserPwd(@RequestBody MemberFindPwdRequest memberFindPwdRequest){
        MemberFindPwdResponse memberPwd = memberService.findMemberPwd(memberFindPwdRequest);
        //메일 전송 - 임시 비밀번호로 변경 후 임시 비밀번호 전송
        return ResponseEntity.ok().body(memberPwd);
    }

    //비밀번호 변경
    @PutMapping("/password")
    public ResponseEntity<?> updatePwd(@RequestBody MemberUpdatePwdRequest memberUpdatePwdRequest){
        memberService.updateMemberPwd(memberUpdatePwdRequest);
        return ResponseEntity.ok().body(null);
    }*/

    //자산 조회

    //부동산 판매하기

    //보유 부동산 조회

    //회원 랭킹 조회

    //전체 랭킹 조회

    // 회원가입
    @PostMapping
    public ResponseEntity<Void> registerMember(@Valid @RequestBody MemberCreateRequest memberCreateRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }

        try{
            checkCreateDuplicated(memberCreateRequest);
        }
        catch (IllegalArgumentException e){

        }


        memberService.registerMember(memberCreateRequest);

        return ResponseEntity.ok().body(null);
    }

    private void checkCreateDuplicated(MemberCreateRequest memberCreateRequest) {
        if(!memberService.isIdDuplicated(memberCreateRequest.id())){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if(memberService.isEmailDuplicated(memberCreateRequest.email())){
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        if(memberService.isNameDuplicated(memberCreateRequest.name())){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

    private void checkUpdateDuplicated(MemberUpdateRequest memberUpdateRequest) {
        if(memberService.isEmailDuplicated(memberUpdateRequest.email())){
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        }
        if(memberService.isNameDuplicated(memberUpdateRequest.name())){
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }


}
