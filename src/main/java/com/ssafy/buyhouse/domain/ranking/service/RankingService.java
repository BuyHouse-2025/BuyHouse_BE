package com.ssafy.buyhouse.domain.ranking.service;

import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseListResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseResponseDto;
import com.ssafy.buyhouse.domain.estate.repository.OwnedHouseRepository;
import com.ssafy.buyhouse.domain.estate.service.HouseService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import com.ssafy.buyhouse.domain.ranking.domain.MemberRanking;
import com.ssafy.buyhouse.domain.ranking.dto.response.MemberPercentileResponseDto;
import com.ssafy.buyhouse.domain.ranking.repository.MemberRankingRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final MemberRankingRepository memberRankingRepository;
    private final MemberRepository memberRepository;
    private final HouseService houseService;

    @PostConstruct
    @Transactional
    public void initRankingIfEmpty() {
        if (memberRankingRepository.count() == 0) {
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

            memberRankingRepository.saveAll(rankings);
        }
    }

    public List<MemberRanking> getRanking(){
        long count = memberRankingRepository.count();
        return memberRankingRepository.findAll(Sort.by(Sort.Direction.ASC, "ranking")).subList(0, (int) Math.min(7,count));
    }

    public MemberPercentileResponseDto calculateMemberPercentile(Member member) {
        Optional<MemberRanking> optionalRanking = memberRankingRepository.findByMember(member);
        long total = memberRankingRepository.count();

        if (optionalRanking.isEmpty() || total == 0) {
            return new MemberPercentileResponseDto(member.getId(), -1, 0.0);
        }

        MemberRanking ranking = optionalRanking.get();
        return MemberPercentileResponseDto.from(ranking, total);
    }

}
