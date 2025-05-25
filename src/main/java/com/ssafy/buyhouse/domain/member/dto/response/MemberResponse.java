package com.ssafy.buyhouse.domain.member.dto.response;

import com.ssafy.buyhouse.domain.member.domain.Member;


public record MemberResponse(
        String name,
        Long cash,
        Long estateAsset,
        Long totalAsset

) {
    public static MemberResponse from(Member member, Long estatePrice) {
        return new MemberResponse(member.getName(), member.getCash(), estatePrice, member.getCash()+estatePrice);
    }
}

