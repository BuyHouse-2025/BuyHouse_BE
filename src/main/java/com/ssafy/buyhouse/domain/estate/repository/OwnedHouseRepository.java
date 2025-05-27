package com.ssafy.buyhouse.domain.estate.repository;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import com.ssafy.buyhouse.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnedHouseRepository extends JpaRepository<OwnedHouse,Long> {
    List<OwnedHouse> findAllByMember_Id(String userId);

    Optional<OwnedHouse> findByMemberAndHouseInfo(Member member, HouseInfo houseInfo);
}
