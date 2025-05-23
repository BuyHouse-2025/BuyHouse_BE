package com.ssafy.buyhouse.domain.wish.repository;


import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.wish.domain.WishHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<WishHouse, Long> {
    List<WishHouse> findAllByMember(Member member);
}
