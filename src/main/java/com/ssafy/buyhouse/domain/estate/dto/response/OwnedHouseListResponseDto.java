package com.ssafy.buyhouse.domain.estate.dto.response;

import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OwnedHouseListResponseDto {

    private List<OwnedHouseResponseDto> ownedHouseList;
    private Integer totalOwnedPrice;  // 총 취득가격
    private Integer totalCurrentPrice; // 총 평가가격
    private Integer totalPriceDifference; // 총 취득가 대비 증감맥
    private Double meanPriceDifferenceRate; // 총 취득가 대비 증감율

    public static OwnedHouseListResponseDto from(List<OwnedHouseResponseDto> houseList) {
        // 총 소유 가격, 총 현재 가격, 총 증감액 계산
        IntSummaryStatistics statsOwned = houseList.stream()
                .collect(Collectors.summarizingInt(OwnedHouseResponseDto::getOwnedPrice));
        IntSummaryStatistics statsCurrent = houseList.stream()
                .collect(Collectors.summarizingInt(OwnedHouseResponseDto::getCurrentPrice));
        IntSummaryStatistics statsDiff = houseList.stream()
                .collect(Collectors.summarizingInt(OwnedHouseResponseDto::getPriceDifference));

        // 증감률(rate) 계산: 각 주택별 (priceDifference / ownedPrice * 100)
        double averageRate = houseList.stream()
                .filter(dto -> dto.getOwnedPrice() != 0)
                .mapToDouble(dto -> dto.getPriceDifference() * 100.0 / dto.getOwnedPrice())
                .average()
                .orElse(0);

        return OwnedHouseListResponseDto.builder()
                .ownedHouseList(houseList)
                .totalOwnedPrice((int) statsOwned.getSum())
                .totalCurrentPrice((int) statsCurrent.getSum())
                .totalPriceDifference((int) statsDiff.getSum())
                .meanPriceDifferenceRate(averageRate)
                .build();
    }

}
