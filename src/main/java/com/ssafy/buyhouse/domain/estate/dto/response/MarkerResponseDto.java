package com.ssafy.buyhouse.domain.estate.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkerResponseDto {

    private String aptSeq;
    private String aptNm;
    private double lat;
    private double lng;
}
