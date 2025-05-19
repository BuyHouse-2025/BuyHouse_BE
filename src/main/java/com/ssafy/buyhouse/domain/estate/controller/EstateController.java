package com.ssafy.buyhouse.domain.estate.controller;

import com.ssafy.buyhouse.domain.estate.domain.Houseinfo;
import com.ssafy.buyhouse.domain.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/estate")
@RequiredArgsConstructor
public class EstateController {

    private final EstateService estateService;

    // 아파트명으로 아파트 검색
    @GetMapping()
    public ResponseEntity<List<Houseinfo>> getHouseListByName(@RequestParam("name") String aptName){

        List<Houseinfo> houseListByName = estateService.getHouseListByName(aptName);

        if(houseListByName.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(houseListByName);
    }

    // 가격으로 아파트 검색 -> 아파트 AI로 최근 거래가 예측한다음에 예측한 최근거래가로 아파트 검색해아함, 가격은 매물에만 있음
    @GetMapping()
    public ResponseEntity<List<Houseinfo>> getHouseListByPrice(@RequestParam("minPrice") int min,
                                                               @RequestParam("maxPrice") int max){

        List<Houseinfo> houseListByPrice = estateService.getHouseListByPrice(min, max);

        if(houseListByPrice.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(houseListByPrice);
    }

    // 평형으로 아파트 검색 이거 없애야할듯 -> 평형은 매물, 매물정보에도 평형은 없음


    // 지역(동)으로 아파트 검색

    // 위경도로 아파트 검색(맵이동)

    // 아파트 정보 상세 검색(매물포함)

    // 아파트 구매하기
}
