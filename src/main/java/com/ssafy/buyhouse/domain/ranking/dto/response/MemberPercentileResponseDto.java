package com.ssafy.buyhouse.domain.ranking.dto.response;

import com.ssafy.buyhouse.domain.ranking.domain.MemberRanking;

public record MemberPercentileResponseDto(
        String memberId,
        int rank,
        double percentile // 예: 97.3 → 상위 2.7%
) {
    public static MemberPercentileResponseDto from(MemberRanking ranking, long totalMembers) {
        double p = 100.0 * (1 - ((double) ranking.getRanking() - 1) / totalMembers);
        double rounded = Math.round(p * 10) / 10.0;
        return new MemberPercentileResponseDto(
                ranking.getMember().getId(),
                ranking.getRanking(),
                rounded
        );
    }
}
