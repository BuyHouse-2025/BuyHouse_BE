package com.ssafy.buyhouse.domain.estate.repository;

import com.ssafy.buyhouse.domain.estate.domain.Houseinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EstateDao {

    // 아파트명으로 아파트 검색
    List<Houseinfo> getHouseListByName(String kaptName);

    // 가격으로 아파트 검색
    List<Houseinfo> getHouseListByPrice(int min, int max);

    // 평형으로 아파트 검색

    // 지역(동)으로 아파트 검색

    // 위경도로 아파트 검색(맵이동)

    // 아파트 정보 상세 검색(매물포함)

    // 아파트 구매하기
}
