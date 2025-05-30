package com.ssafy.buyhouse.domain.wish.dto.response;

import com.ssafy.buyhouse.domain.wish.domain.WishHouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishHouseResponseDto {
    private String aptSeq;
    private String aptNm;
    private String roadNm;
    private String roadNmBonbun;
    private String roadNmBubun;

    public static WishHouseResponseDto from(WishHouse wishHouse) {
        return WishHouseResponseDto.builder()
                .aptSeq(wishHouse.getHouseInfo().getAptSeq())
                .aptNm(wishHouse.getHouseInfo().getAptNm())
                .roadNm(wishHouse.getHouseInfo().getRoadNm())
                .roadNmBonbun(wishHouse.getHouseInfo().getRoadNmBonbun())
                .roadNmBubun(wishHouse.getHouseInfo().getRoadNmBubun())
                .build();
    }

}
