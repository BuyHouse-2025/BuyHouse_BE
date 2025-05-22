package com.ssafy.buyhouse.domain.estate.service;

import com.ssafy.buyhouse.domain.board.domain.Board;
import com.ssafy.buyhouse.domain.estate.domain.HouseDeal;
import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import com.ssafy.buyhouse.domain.estate.dto.request.PurchaseRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseDetailResponseDto;
import com.ssafy.buyhouse.domain.estate.dto.response.HouseResponseDto;
import com.ssafy.buyhouse.domain.estate.repository.HouseDealRepository;
import com.ssafy.buyhouse.domain.estate.repository.HouseRepository;
import com.ssafy.buyhouse.domain.estate.repository.OwnedHouseRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseDealRepository houseDealRepository;
    private final OwnedHouseRepository ownedHouseRepository;
    // private final MemberRepsitory memberRepsitory; 맴버 생성한후 추가

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
    public String purchaseHouse(PurchaseRequestDto purchaseRequestDto) {

       //  Member member = memberRepsitory.findById(purchaseRequestDto.getUserId()
        //  .orElseThrow(() -> new EntityNotFoundException("회원이 없습니다. userId=" + req.getUserId()));) 맴버 생성한후 추가

        HouseDeal deal = houseDealRepository.findById(purchaseRequestDto.getDealId())
                .orElseThrow(() -> new EntityNotFoundException("거래 정보가 없습니다. dealId=" + purchaseRequestDto.getDealId()));

        OwnedHouse ownedHouse = OwnedHouse.builder()
                //.member(member)
                .deal(deal)
                .build();
        ownedHouseRepository.save(ownedHouse);

        return "부동산 매물 구매를 완료하였습니다.";
    }
}
