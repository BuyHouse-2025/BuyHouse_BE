package com.ssafy.buyhouse.domain.interest.repository;

import com.ssafy.buyhouse.domain.interest.domain.Dongcode;
import com.ssafy.buyhouse.domain.interest.domain.Interest;
import com.ssafy.buyhouse.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
    List<Interest> findByMember(Member member);

    boolean existsByDongcodeAndMember(Dongcode dongcode, Member member);

    List<Interest> getInterestById(Integer id);
}
