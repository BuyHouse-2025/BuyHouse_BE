package com.ssafy.buyhouse.domain.member.service;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.domain.PwdQuestion;
import com.ssafy.buyhouse.domain.member.domain.UserType;
import com.ssafy.buyhouse.domain.member.dto.reqeust.*;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindIdResponse;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindPwdResponse;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import com.ssafy.buyhouse.domain.member.repository.PwdQuestionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PwdQuestionRepository pwdQuestionRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void registerMember(@Valid MemberCreateRequest memberCreateRequest) {
        PwdQuestion question = pwdQuestionRepository.findById(memberCreateRequest.pwdQuestion())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 질문 ID입니다."));

        Member member = Member.builder()
                .id(memberCreateRequest.id())
                .password(passwordEncoder.encode(memberCreateRequest.password()))
                .email(memberCreateRequest.email())
                .name(memberCreateRequest.name())
                .birthDate(memberCreateRequest.birthday())
                .phoneNumber(memberCreateRequest.phoneNumber())
                .pwdQuestion(question)
                .pwdAnswer(memberCreateRequest.pwdAnswer())
                .cash(5_000_000_000L)
                .type(UserType.NORMAL)
                .build();

        memberRepository.save(member);
    }

    public boolean isIdDuplicated(String id) {
        return memberRepository.existsMemberById(id);
    }

    public boolean isNameDuplicated(String name) {
        return memberRepository.existsMemberByName(name);
    }

    public boolean isNameDuplicated(Member member, String name) {
        Optional<Member> byName = memberRepository.findByName(name);
        if(byName.isEmpty()) return false;
        if(member.getId().equals(byName.get().getId())) return false;
        return true;
    }

    public boolean isEmailDuplicated(String email) {
        return memberRepository.existsMemberByEmail(email);
    }

    public boolean isEmailDuplicated(Member member, String email) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if(byEmail.isEmpty()) return false;
        if(member.getId().equals(byEmail.get().getId())) return false;
        return true;
    }

    @Transactional
    public void updateMember(MemberUpdateRequest memberUpdateRequest, Member member) {
        if(memberUpdateRequest.email() != null){
            member.setEmail(memberUpdateRequest.email());
        }
        if(memberUpdateRequest.birthday() != null){
            member.setBirthDate(memberUpdateRequest.birthday());
        }
        if(memberUpdateRequest.name() != null){
            member.setName(memberUpdateRequest.name());
        }
        if(memberUpdateRequest.phoneNumber() != null){
            member.setPhoneNumber(memberUpdateRequest.phoneNumber());
        }
        if(memberUpdateRequest.pwdQuestion() != null){
            PwdQuestion question = pwdQuestionRepository.findById(memberUpdateRequest.pwdQuestion())
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 질문 ID입니다."));
            member.setPwdQuestion(question);
        }
        if(memberUpdateRequest.pwdAnswer() != null){
            member.setPwdAnswer(memberUpdateRequest.pwdAnswer());
        }
    }

    public Member findMemberById(String id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public Member findMemberId(MemberFindIdRequest memberFindIdRequest) {
        Member member = memberRepository.findByEmail(memberFindIdRequest.email()).orElseThrow();

        if(member.getName().equals(memberFindIdRequest.name())
                && member.getBirthDate().equals(memberFindIdRequest.getBirthDate())
                && member.getPhoneNumber().equals(memberFindIdRequest.phoneNumber())) return member;
        else throw new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다");
    }

    public Member findMemberPwd(MemberFindPwdRequest memberFindPwdRequest) {
        Member member = memberRepository.findByEmail(memberFindPwdRequest.email()).orElseThrow();

        if(member.getName().equals(memberFindPwdRequest.name())
                && member.getId().equals(memberFindPwdRequest.id())
                && member.getBirthDate().equals(memberFindPwdRequest.getBirthDate())
                && member.getPhoneNumber().equals(memberFindPwdRequest.phoneNumber())
                && member.getPwdQuestion().getId().equals(memberFindPwdRequest.pwdQuestion())
                && member.getPwdAnswer().equals(memberFindPwdRequest.pwdAnswer())) return member;
        else throw new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다");
    }

    @Transactional
    public void updateMemberPwd(MemberUpdatePwdRequest req, Member member) {
        String oldPwd = req.passwordOrigin();
        String dbPwd = member.getPassword();

        if (!passwordEncoder.matches(oldPwd, dbPwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        if (!req.passwordNew().equals(req.passwordNewCheck())) {
            throw new IllegalArgumentException("새 비밀번호가 다릅니다");
        }

        member.setPassword(passwordEncoder.encode(req.passwordNew()));
    }


    @Transactional
    public void setMemberPwdTemp(String tempPassword, Member member) {
        member.setPassword(passwordEncoder.encode(tempPassword));
    }

    @Transactional
    public void changePassword(String memberId, MemberUpdatePwdRequest memberUpdatePwdRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(memberUpdatePwdRequest.passwordOrigin(), member.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        if(!memberUpdatePwdRequest.passwordNew().equals(memberUpdatePwdRequest.passwordNewCheck())){
            throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        }

        String encodedNewPassword = passwordEncoder.encode(memberUpdatePwdRequest.passwordNew());
        member.setPassword(encodedNewPassword);
    }

    @Transactional
    public void deleteByEntity(Member member) {
        System.out.println("삭제 : " + member.getId());
        memberRepository.deleteById(member.getId());
    }

    public boolean isPasswordSame(String pwd, String pwdCheck){
        if(pwd.equals(pwdCheck)) return true;
        else throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}
