package com.ssafy.buyhouse.domain.estate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PredictPriceRequestDto {
    private int floor;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private int excluUseAr;

}
