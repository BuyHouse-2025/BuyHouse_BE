package com.ssafy.buyhouse.domain.interest.dto.response;

import com.ssafy.buyhouse.domain.interest.domain.Dongcode;
import com.ssafy.buyhouse.domain.interest.domain.Interest;

public record InterestResponse(
        String dongcode,
        Integer id,
        String sido,
        String gugun,
        String dong,
        Double lat,
        Double lng
) {
    public static InterestResponse from(Interest interest){
        Dongcode dongcode = interest.getDongcode();
        return new InterestResponse(dongcode.getDongCode(), interest.getId(), dongcode.getSido(), dongcode.getGugun(), dongcode.getDong(), dongcode.getLatitude(), dongcode.getLongitude());
    }
}
