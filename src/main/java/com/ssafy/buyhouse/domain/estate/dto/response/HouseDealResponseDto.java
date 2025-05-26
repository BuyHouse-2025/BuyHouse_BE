package com.ssafy.buyhouse.domain.estate.dto.response;

import com.ssafy.buyhouse.domain.estate.domain.HouseDeal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HouseDealResponseDto {

    private String aptDong;
    private String floor;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private Double excluUseAr;
    private String dealAmount;

    public static HouseDealResponseDto from(HouseDeal houseDeal) {
        return HouseDealResponseDto.builder()
                .floor(houseDeal.getFloor())
                .dealYear(houseDeal.getDealYear())
                .dealMonth(houseDeal.getDealMonth())
                .dealDay(houseDeal.getDealDay())
                .excluUseAr(houseDeal.getExcluUseAr())
                .dealAmount(houseDeal.getDealAmount())
                .build();
    }
}
