package com.ssafy.buyhouse.domain.member.service;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.dto.reqeust.*;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindIdResponse;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindPwdResponse;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void registerMember(@Valid MemberCreateRequest memberCreateRequest) {
        memberRepository.save(memberCreateRequest.toEntity());
    }

    public boolean isIdDuplicated(String id) {
        return memberRepository.existsMemberById(id);
    }

    public boolean isNameDuplicated(String name) {
        return memberRepository.existsMemberByName(name);
    }

    public boolean isEmailDuplicated(String email) {
        return memberRepository.existsMemberByEmail(email);
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
            member.setPwdQuestion(memberUpdateRequest.pwdQuestion());
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

        System.out.println(member.getName());
        System.out.println(memberFindIdRequest.name());
        System.out.println(member.getBirthDate());
        System.out.println(memberFindIdRequest.getBirthDate());
        System.out.println(member.getEmail());
        System.out.println(memberFindIdRequest.email());
        System.out.println(member.getPhoneNumber());
        System.out.println(memberFindIdRequest.phoneNumber());

        //생년월일 포맷팅하기

        if(member.getName().equals(memberFindIdRequest.name())
            && member.getBirthDate().equals(memberFindIdRequest.getBirthDate())
            && member.getPhoneNumber().equals(memberFindIdRequest.phoneNumber())) return member;
        else throw new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다");
    }

    public Member findMemberPwd(MemberFindPwdRequest memberFindPwdRequest) {
        Member member = memberRepository.findByEmail(memberFindPwdRequest.email()).orElseThrow();

        System.out.println(member.getName());
        System.out.println(memberFindPwdRequest.name());
        System.out.println(member.getBirthDate());
        System.out.println(memberFindPwdRequest.getBirthDate());
        System.out.println(member.getEmail());
        System.out.println(memberFindPwdRequest.email());
        System.out.println(member.getPhoneNumber());
        System.out.println(memberFindPwdRequest.phoneNumber());

        System.out.println(member.getPwdQuestion());
        System.out.println(memberFindPwdRequest.pwdQuestion());
        System.out.println(member.getPwdAnswer());
        System.out.println(memberFindPwdRequest.pwdAnswer());



        if(member.getName().equals(memberFindPwdRequest.name())
                && member.getId().equals(memberFindPwdRequest.id())
                && member.getBirthDate().equals(memberFindPwdRequest.getBirthDate())
                && member.getPhoneNumber().equals(memberFindPwdRequest.phoneNumber())
                && member.getPwdQuestion().equals(memberFindPwdRequest.pwdQuestion())
                && member.getPwdAnswer().equals(memberFindPwdRequest.pwdAnswer())) return member;
        else throw new IllegalArgumentException("해당하는 사용자가 존재하지 않습니다");
    }

    @Transactional
    public void updateMemberPwd(MemberUpdatePwdRequest memberUpdatePwdRequest, Member member) {
        String oldPwd = memberUpdatePwdRequest.passwordOrigin();
        System.out.println(passwordEncoder.encode(memberUpdatePwdRequest.passwordOrigin()));

        if(!passwordEncoder.matches(oldPwd, member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        if(!memberUpdatePwdRequest.passwordNew().equals(memberUpdatePwdRequest.passwordNewCheck())){
            throw new IllegalArgumentException("새 비밀번호가 다릅니다");
        }



        member.setPassword(passwordEncoder.encode(memberUpdatePwdRequest.passwordNew()));
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

}
