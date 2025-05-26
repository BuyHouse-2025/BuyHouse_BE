package com.ssafy.buyhouse.domain.ranking.service;

import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseListResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseResponseDto;
import com.ssafy.buyhouse.domain.estate.service.HouseService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import com.ssafy.buyhouse.domain.ranking.domain.MemberRanking;
import com.ssafy.buyhouse.domain.ranking.repository.MemberRankingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingScheduler {

    private final MemberRepository memberRepository;
    private final HouseService houseService;
    private final MemberRankingRepository rankingRepository;

    @Scheduled(cron = "0 0 0 * * SUN") // 매주 일요일 00:00
    @Transactional
    public void updateWeeklyRanking() {
        List<Member> members = memberRepository.findAll();

        List<MemberRanking> rankings = members.stream().map(member -> {
                    OwnedHouseListResponseDto dto = houseService.searchOwnedHouse(member.getId());

                    Long houseTotal = dto.getOwnedHouseList().stream()
                            .mapToLong(OwnedHouseResponseDto::getCurrentPrice)
                            .sum();

                    Long totalAsset = member.getCash() + houseTotal;
                    Double roi = dto.getMeanPriceDifferenceRate();
                    return new MemberRanking(member, totalAsset, 0, roi);
                }).sorted((a, b) -> Long.compare(b.getTotalAsset(), a.getTotalAsset()))
                .toList();

        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRanking(i + 1);
        }

        rankingRepository.deleteAllInBatch();
        rankingRepository.saveAll(rankings);
    }
}
