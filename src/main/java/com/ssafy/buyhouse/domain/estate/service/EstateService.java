package com.ssafy.buyhouse.domain.estate.service;

import com.ssafy.buyhouse.domain.estate.domain.Houseinfo;
import com.ssafy.buyhouse.domain.estate.repository.EstateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class EstateService {

    private final EstateDao estateDao;


    // 아파트명으로 아파트 정보 검색
    public List<Houseinfo> getHouseListByName(String kaptName) {

        return estateDao.getHouseListByName(kaptName);
    }

    // 가격으로 아파트 검색
    public List<Houseinfo> getHouseListByPrice(int min, int max){

        return estateDao.getHouseListByPrice(min, max);
    }

    // 평형으로 아파트 검색

    // 지역(동)으로 아파트 검색

    // 위경도로 아파트 검색(맵이동)

    // 아파트 정보 상세 검색(매물포함)

    // 아파트 구매하기
}
