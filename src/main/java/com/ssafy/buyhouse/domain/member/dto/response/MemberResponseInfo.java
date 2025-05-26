package com.ssafy.buyhouse.domain.member.dto.response;

import com.ssafy.buyhouse.domain.member.domain.Member;


public record MemberResponseInfo(

        String email,

        String name,

        String birthday,

        String phoneNumber,

        Integer pwdQuestion,

        String pwdAnswer

) {
    public static MemberResponseInfo from(Member member) {
        return new MemberResponseInfo(member.getEmail(), member.getName(), member.getBirthDate(), member.getPhoneNumber(), member.getPwdQuestion().getId(), member.getPwdAnswer());
    }
}

