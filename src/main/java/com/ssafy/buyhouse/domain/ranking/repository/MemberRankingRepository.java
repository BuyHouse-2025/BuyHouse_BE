package com.ssafy.buyhouse.domain.ranking.repository;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.ranking.domain.MemberRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRankingRepository extends JpaRepository<MemberRanking, Long> {
    Optional<MemberRanking> findByMember(Member member);
}
