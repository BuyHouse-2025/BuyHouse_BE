package com.ssafy.buyhouse.domain.member.service;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.dto.reqeust.MemberCreateRequest;
import com.ssafy.buyhouse.domain.member.dto.reqeust.MemberUpdateRequest;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

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
}
