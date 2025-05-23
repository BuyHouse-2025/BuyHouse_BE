package com.ssafy.buyhouse.domain.estate.repository;

import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnedHouseRepository extends JpaRepository<OwnedHouse,Long> {
    List<OwnedHouse> findAllByMember_Id(String userId);
}
