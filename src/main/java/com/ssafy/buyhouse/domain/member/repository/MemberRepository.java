package com.ssafy.buyhouse.domain.member.repository;


import com.ssafy.buyhouse.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
}
