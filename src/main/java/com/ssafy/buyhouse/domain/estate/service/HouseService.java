package com.ssafy.buyhouse.domain.estate.service;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseDetailResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseListResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.OwnedHouseResponseDto;
import com.ssafy.buyhouse.domain.estate.repository.HouseDealRepository;
import com.ssafy.buyhouse.domain.estate.repository.HouseRepository;
import com.ssafy.buyhouse.domain.estate.repository.OwnedHouseRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseService {
    private final HouseRepository houseRepository;
    private final OwnedHouseRepository ownedHouseRepository;

    // 부동산 검색 - 이름,가격,평형
    public List<HouseResponseDto> searchName(SearchRequestDto searchRequestDto) {

        List<HouseInfo> houseinfos =  houseRepository.findBySearchInfo(
                searchRequestDto.getAptNm(),
                searchRequestDto.getMinPrice(),
                searchRequestDto.getMaxPrice(),
                searchRequestDto.getMinSquare(),
                searchRequestDto.getMaxSquare()
                );

        return houseinfos.stream().map(HouseResponseDto::from)
                .collect(Collectors.toList());
    }

    // 부동산 상세 정보 조회
    public HouseDetailResponseDto findByAptSeq(String aptSeq) {
        HouseInfo houseInfo = houseRepository.findById(aptSeq)
                .orElseThrow(() -> new EntityNotFoundException("단지 정보가 없습니다. aptSeq=" + aptSeq));
        return HouseDetailResponseDto.from(houseInfo);
    }

    // 부동산 구매하기
    public String purchaseHouse(String aptSeq, String member) { // 멤버 추가후 수정

        HouseInfo houseInfo = houseRepository.findById(aptSeq)
                .orElseThrow(() -> new EntityNotFoundException("단지 정보가 없습니다. aptSeq=" + aptSeq));

        OwnedHouse ownedHouse = OwnedHouse.builder()
                //.member(member)
                .houseInfo(houseInfo)
                .build();
        ownedHouseRepository.save(ownedHouse);

        return "부동산 매물 구매를 완료하였습니다.";
    }

    // 보유 부동산 조회하기
    public OwnedHouseListResponseDto searchOwnedHouse(String userId) {
        return OwnedHouseListResponseDto.from(ownedHouseRepository.findAllByMember_Id(userId)
                .stream().map(OwnedHouseResponseDto::from)
                .collect(Collectors.toList()));
    }

    // 보유 부동산 판매하기
    public String SaleHouse(Long id) {
        OwnedHouse ownedHouse = ownedHouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 보유 부동산이 없습니다. id=" + id));

        // 맴버 소지금 변동
        // Member member = ownedHouse.getMember();
        // member.CashConversion(ownedHouse.getOwnedPrice());

        return "부동산 판매가 완료되었습니다.";
    }

}
