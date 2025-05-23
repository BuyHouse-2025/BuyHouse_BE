package com.ssafy.buyhouse.domain.estate.repository;

import com.ssafy.buyhouse.domain.estate.domain.HouseDeal;
import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {
}
