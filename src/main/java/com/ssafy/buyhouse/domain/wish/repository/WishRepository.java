package com.ssafy.buyhouse.domain.wish.repository;


import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.wish.domain.WishHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<WishHouse, Long> {
    List<WishHouse> findAllByMember(Member member);

    Optional<WishHouse> findByMemberAndHouseInfo(Member member, HouseInfo houseInfo);
}
