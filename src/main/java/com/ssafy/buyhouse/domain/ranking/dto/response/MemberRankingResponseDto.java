package com.ssafy.buyhouse.domain.ranking.dto.response;

import com.ssafy.buyhouse.domain.ranking.domain.MemberRanking;

public record MemberRankingResponseDto(
        String memberId,
        String name,
        Long totalAsset,
        Integer ranking,
        Double roi
) {
    public static MemberRankingResponseDto from(MemberRanking ranking) {
        return new MemberRankingResponseDto(
                ranking.getMember().getId(),
                ranking.getMember().getName(),
                ranking.getTotalAsset(),
                ranking.getRanking(),
                ranking.getRoi()
        );
    }
}
