package com.ssafy.buyhouse.domain.interest.dto.response;

import com.ssafy.buyhouse.domain.interest.domain.Dongcode;

public record DongcodeResponse(
        String dongcode,
        String sido,
        String gugun,
        String dong,
        Double lat,
        Double lng
) {
    public static DongcodeResponse from(Dongcode d) {
        return new DongcodeResponse(
                d.getDongCode(),
                d.getSido(),
                d.getGugun(),
                d.getDong(),
                d.getLatitude(),
                d.getLongitude()
        );
    }
}
