package com.ssafy.buyhouse.domain.interest.repository;

import com.ssafy.buyhouse.domain.interest.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
}
