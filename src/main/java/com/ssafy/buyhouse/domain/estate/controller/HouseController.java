package com.ssafy.buyhouse.domain.estate.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.estate.dto.request.PredictPriceRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseDetailResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseListResponseDto;
import com.ssafy.buyhouse.domain.estate.service.HouseService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estate")
public class HouseController {

    private final HouseService houseService;

    // 부동산 검색 - 이름,가격,평형
    @PostMapping
    public ResponseEntity<List<HouseResponseDto>> HouseInfoSearchName(@RequestBody SearchRequestDto searchRequestDto) {
        List<HouseResponseDto> houseList = houseService.searchName(searchRequestDto);
        return ResponseEntity.ok().body(houseList);
    }


    // 부동산 상세 정보 조회
    @GetMapping("/{aptSeq}")
    public ResponseEntity<HouseDetailResponseDto> GetHouseDetailInfo(@PathVariable String aptSeq) {
        HouseDetailResponseDto houseDetailResponseDto = houseService.findByAptSeq(aptSeq);
        return  ResponseEntity.ok().body(houseDetailResponseDto);
    }

    // 부동산 구매하기
    @PostMapping("/{aptSeq}/purchase")
    public ResponseEntity<?> Postpurchase(
            @PathVariable String aptSeq, @LoginUser Member member,
            @RequestBody PredictPriceRequestDto predictPriceRequestDto) { // 유저 정보 추가하고 수정
        String result = houseService.purchaseHouse(aptSeq, member, predictPriceRequestDto);
        return ResponseEntity.ok().body(result);
    }

    // 보유 부동산 조회하기
    @GetMapping("/owned")
    public ResponseEntity<OwnedHouseListResponseDto> GetOwnedHouseInfo(@LoginUser Member member) {
        OwnedHouseListResponseDto ownedHouseListResponseDto = houseService.searchOwnedHouse(member.getId());
        return ResponseEntity.ok().body(ownedHouseListResponseDto);
    }

    // 보유 부동산 판매하기
    @DeleteMapping("/owned/{id}")
    public ResponseEntity<?> DeleteOwnedHouse(@PathVariable long id, @LoginUser Member member) {
        try {
            String result = houseService.SaleHouse(id, member);
            return ResponseEntity.ok().body(result);
        } catch (IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
