package com.ssafy.buyhouse.domain.member.dto.response;

import com.ssafy.buyhouse.domain.member.domain.Member;

public record MemberFindIdResponse (String name, String id){

    public static MemberFindIdResponse from(Member member){
        return new MemberFindIdResponse(member.getName(), member.getId());
    }

}
