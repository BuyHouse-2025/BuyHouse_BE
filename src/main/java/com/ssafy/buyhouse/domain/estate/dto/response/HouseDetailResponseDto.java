package com.ssafy.buyhouse.domain.estate.dto.response;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HouseDetailResponseDto {

    private String roadNm;
    private String roadNmBonbun;
    private String roadNmBubun;
    private String aptNm;
    private int buildYear;
    private BigDecimal minArea;
    private BigDecimal maxArea;
    private BigDecimal representativeArea;
    private int floorAreaRatio;
    private int parking;
    private int bus;
    private int metro;
    private int hospital;
    private int mart;
    private int convenience;
    private int infant;
    private int preschool;
    private int priSchool;
    private int pubSchool;
    private int naverMinDeal;
    private int naverMaxDeal;
    private int predictPrice;

    private List<HouseDealResponseDto> deals;

    public static HouseDetailResponseDto from(HouseInfo hi) {
        var d = hi.getDetailInfo();
        List<HouseDealResponseDto> dealResponseDtos = hi.getDeals().stream()
                .map(HouseDealResponseDto::from)
                .collect(Collectors.toList());

        return HouseDetailResponseDto.builder()
                .aptNm(hi.getAptNm())
                .roadNm(hi.getRoadNm())
                .roadNmBonbun(hi.getRoadNmBonbun())
                .roadNmBubun(hi.getRoadNmBubun())
                .buildYear(hi.getBuildYear())
                .minArea(d.getMinArea())
                .maxArea(d.getMaxArea())
                .representativeArea(d.getRepresentativeArea())
                .floorAreaRatio(d.getFloorAreaRatio())
                .parking(d.getParking())
                .bus(d.getBus())
                .metro(d.getMetro())
                .hospital(d.getHospital())
                .mart(d.getMart())
                .convenience(d.getConvenience())
                .infant(d.getInfant())
                .preschool(d.getPreschool())
                .priSchool(d.getPriSchool())
                .pubSchool(d.getPubSchool())
                .naverMinDeal(d.getNaverMinDeal())
                .naverMaxDeal(d.getNaverMaxDeal())
                .deals(dealResponseDtos)
                .predictPrice(d.getPredictPrice())
                .build();
    }


}
