package com.ssafy.buyhouse.domain.member.repository;


import com.ssafy.buyhouse.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    boolean existsMemberById(String id);

    boolean existsMemberByName(String name);

    boolean existsMemberByEmail(String email);

    Optional<Member> findByName(String username);

    Optional<Member> findByEmail(String email);
}
