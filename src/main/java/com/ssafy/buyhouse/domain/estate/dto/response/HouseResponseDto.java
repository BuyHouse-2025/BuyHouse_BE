package com.ssafy.buyhouse.domain.estate.dto.response;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDto {

    private String umdNm;
    private String jibun;
    private String roadNm;
    private String roadNmBonbun;
    private String roadNmbubun;
    private String aptNm;
    private int buildYear;
    private String latitude;
    private String longitude;

    public static HouseResponseDto from(HouseInfo houseInfo) {
        return HouseResponseDto.builder()
                .umdNm(houseInfo.getUmdNm())
                .jibun(houseInfo.getJibun())
                .roadNm(houseInfo.getRoadNm())
                .roadNmBonbun(houseInfo.getRoadNmBonbun())
                .roadNmbubun(houseInfo.getRoadNmBubun())
                .aptNm(houseInfo.getAptNm())
                .buildYear(houseInfo.getBuildYear())
                .latitude(houseInfo.getLatitude())
                .longitude(houseInfo.getLongitude())
                .build();
    }
}
