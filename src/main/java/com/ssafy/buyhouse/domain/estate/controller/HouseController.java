package com.ssafy.buyhouse.domain.estate.controller;

import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseResponseDto;
import com.ssafy.buyhouse.domain.estate.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estate")
public class HouseController {

    private final HouseService houseService;

    // 부동산 검색 - 이름,가격,평형
    @PostMapping
    public ResponseEntity<List<HouseResponseDto>> HouseInfoSearchName(@RequestBody SearchRequestDto searchRequestDto) {
        List<HouseResponseDto> houselist = houseService.searchName(searchRequestDto);
        return ResponseEntity.ok().body(houselist);
    }

    // 부동산 맵 이동

    // 부동산 상세 정보 조회

    // 부동산 구매하기

}
