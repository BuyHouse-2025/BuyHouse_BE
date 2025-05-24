package com.ssafy.buyhouse.domain.ranking.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.ranking.domain.MemberRanking;
import com.ssafy.buyhouse.domain.ranking.dto.response.MemberPercentileResponseDto;
import com.ssafy.buyhouse.domain.ranking.dto.response.MemberRankingResponseDto;
import com.ssafy.buyhouse.domain.ranking.repository.MemberRankingRepository;
import com.ssafy.buyhouse.domain.ranking.service.RankingScheduler;
import com.ssafy.buyhouse.domain.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rankings")
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<List<MemberRankingResponseDto>> getRanking() {
        List<MemberRanking> rankingList = rankingService.getRanking();
        return ResponseEntity.ok(rankingList.stream()
                .map(MemberRankingResponseDto::from)
                .collect(Collectors.toList()));
    }

    @GetMapping("/users")
    public ResponseEntity<MemberPercentileResponseDto> getPercentile(@LoginUser Member member) {
        MemberPercentileResponseDto dto = rankingService.calculateMemberPercentile(member);
        return ResponseEntity.ok(dto);
    }


}
