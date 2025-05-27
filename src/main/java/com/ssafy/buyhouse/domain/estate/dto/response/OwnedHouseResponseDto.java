package com.ssafy.buyhouse.domain.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnedHouseResponseDto {

    private String aptNm;
    private int ownedPrice; // 취득가액
    private int currentPrice; // 평가액
    private LocalDateTime purchaseDate; //구매시간

    // 증감액
    @JsonProperty("priceDifference")
    public int getPriceDifference() {
        return ownedPrice - currentPrice;
    }

    // 증감율
    @JsonProperty("priceDifferenceRate")
    public double getPriceDifferenceRate() {
        return (double) (ownedPrice - currentPrice) / 100;
    }

    public static OwnedHouseResponseDto from(OwnedHouse ownedHouse ) {
        return OwnedHouseResponseDto.builder()
                .aptNm(ownedHouse.getHouseInfo().getAptNm())
                .ownedPrice(ownedHouse.getOwnedPrice())
                .currentPrice(ownedHouse.getCurrentPrice())
                .purchaseDate(ownedHouse.getCreatedAt())
                .build();
    }
}
