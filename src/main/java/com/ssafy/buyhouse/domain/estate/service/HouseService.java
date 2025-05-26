package com.ssafy.buyhouse.domain.estate.service;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import com.ssafy.buyhouse.domain.estate.dto.request.MarkerRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.request.PredictPriceRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import com.ssafy.buyhouse.domain.estate.dto.response.*;
import com.ssafy.buyhouse.domain.estate.repository.HouseRepository;
import com.ssafy.buyhouse.domain.estate.repository.OwnedHouseRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.model.service.ModelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseService {
    private final HouseRepository houseRepository;
    private final OwnedHouseRepository ownedHouseRepository;
    private final ModelService modelService;

    // 부동산 맵 조회 - 마커
    public List<MarkerResponseDto> findBymarker(MarkerRequestDto markerRequestDto) {
        List<HouseInfo> houseList = houseRepository.findByLatitudeBetweenAndLongitudeBetween(
                markerRequestDto.getMinLat(),
                markerRequestDto.getMaxLat(),
                markerRequestDto.getMinLng(),
                markerRequestDto.getMaxLng()
        );

        return houseList.stream()
                .map(h -> MarkerResponseDto.builder()
                        .aptSeq(h.getAptSeq())
                        .aptNm(h.getAptNm())
                        .lat(h.getLatitude())
                        .lng(h.getLongitude())
                        .build())
                .toList();
    }


    // 부동산 검색 - 이름,가격,평형
    public List<HouseResponseDto> searchName(SearchRequestDto searchRequestDto) {
        Pageable page10 = PageRequest.of(0, 10);

        Page<HouseInfo> page = houseRepository.findBySearchInfo(
                searchRequestDto.getAptNm(),
                searchRequestDto.getMinPrice(),
                searchRequestDto.getMaxPrice(),
                searchRequestDto.getMinSquare(),
                searchRequestDto.getMaxSquare(),
                page10
        );

        // getContent() 으로 리스트만 꺼내서 DTO로 매핑
        return page.getContent().stream()
                .map(HouseResponseDto::from)
                .collect(Collectors.toList());
    }

    // 부동산 상세 정보 조회
    public HouseDetailResponseDto findByAptSeq(String aptSeq) {
        HouseInfo houseInfo = houseRepository.findById(aptSeq)
                .orElseThrow(() -> new EntityNotFoundException("단지 정보가 없습니다. aptSeq=" + aptSeq));
        return HouseDetailResponseDto.from(houseInfo);
    }

    // 부동산 구매하기
    public String purchaseHouse(
            String aptSeq, Member member,
            PredictPriceRequestDto predictPriceRequestDto) { // 멤버 추가후 수정

        HouseInfo houseInfo = houseRepository.findById(aptSeq)
                .orElseThrow(() -> new EntityNotFoundException("단지 정보가 없습니다. aptSeq=" + aptSeq));

        // ① features 벡터 생성
        double[] features = makeFeatureVector(houseInfo, predictPriceRequestDto);
        // ② 원래 predict 호출
        double predDouble = modelService.predict(features)[0];

        int predInt = (int) Math.round(predDouble);

        OwnedHouse ownedHouse = OwnedHouse.builder()
                .member(member)
                .houseInfo(houseInfo)
                .floor(predictPriceRequestDto.getFloor())
                .useArea((predictPriceRequestDto.getExcluUseAr()))
                .ownedPrice(predInt)
                .currentPrice(predInt)
                .build();
        ownedHouseRepository.save(ownedHouse);

        return "부동산 매물 구매를 완료하였습니다.";
    }

    // 보유 부동산 조회하기
    public OwnedHouseListResponseDto searchOwnedHouse(String userId) {
        List<OwnedHouse> ownedHouses = ownedHouseRepository.findAllByMember_Id(userId);


        // 현재 보유 부동산 가격 예측 모델링
        LocalDate today = LocalDate.now();
        int year  = today.getYear();
        int month = today.getMonthValue();
        int day   = today.getDayOfMonth();

        for(OwnedHouse ownedHouse : ownedHouses) {
            // ① features 벡터 생성
            double[] features = makeFeatureVector(ownedHouse, year, month, day);
            // ② 원래 predict 호출
            double predDouble = modelService.predict(features)[0];

            int predInt = (int) Math.round(predDouble);
            ownedHouse.setCurrentPrice(predInt);
        }
        return OwnedHouseListResponseDto.from(ownedHouses
                .stream().map(OwnedHouseResponseDto::from)
                .collect(Collectors.toList()));
    }

    // 보유 부동산 판매하기
    public String SaleHouse(Long id, Member member) throws IllegalAccessException {
        OwnedHouse ownedHouse = ownedHouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 보유 부동산이 없습니다. id=" + id));

        if(!ownedHouse.getMember().getId().equals(member.getId()))
            throw new IllegalAccessException("해당 사용자가 보유한 부동산이 아닙니다.");

        // 맴버 소지금 변동
        member.CashConversion(ownedHouse.getCurrentPrice());

        ownedHouseRepository.delete(ownedHouse);

        return "부동산 판매가 완료되었습니다.";
    }


    // 부동산 예측 피쳐 메서드
    private double[] makeFeatureVector(HouseInfo h,PredictPriceRequestDto p) {
        return new double[]{
                p.getFloor(),
                p.getDealYear(),
                p.getDealMonth(),
                p.getDealDay(),
                p.getExcluUseAr(),
                h.getDetailInfo().getMinArea(),
                h.getDetailInfo().getMaxArea(),
                h.getDetailInfo().getRepresentativeArea(),
                h.getDetailInfo().getFloorAreaRatio(),
                h.getDetailInfo().getParking(),
                h.getDetailInfo().getBus(),
                h.getDetailInfo().getMetro(),
                h.getDetailInfo().getHospital(),
                h.getDetailInfo().getMart(),
                h.getDetailInfo().getConvenience(),
                h.getDetailInfo().getInfant(),
                h.getDetailInfo().getPreschool(),
                h.getDetailInfo().getNaverMinDeal(),
                h.getDetailInfo().getNaverMaxDeal(),
                h.getDetailInfo().getBuild()
        };
    }
    // 부동산 예측 피쳐 메서드
    private double[] makeFeatureVector(OwnedHouse o, int year, int month, int day) {
        HouseInfo h = o.getHouseInfo();
        return new double[]{
                o.getFloor(),
                o.getUseArea(),
                year,
                month,
                day,
                h.getDetailInfo().getMinArea(),
                h.getDetailInfo().getMaxArea(),
                h.getDetailInfo().getRepresentativeArea(),
                h.getDetailInfo().getFloorAreaRatio(),
                h.getDetailInfo().getParking(),
                h.getDetailInfo().getBus(),
                h.getDetailInfo().getMetro(),
                h.getDetailInfo().getHospital(),
                h.getDetailInfo().getMart(),
                h.getDetailInfo().getConvenience(),
                h.getDetailInfo().getInfant(),
                h.getDetailInfo().getPreschool(),
                h.getDetailInfo().getNaverMinDeal(),
                h.getDetailInfo().getNaverMaxDeal(),
                h.getDetailInfo().getBuild()
        };
    }
}
