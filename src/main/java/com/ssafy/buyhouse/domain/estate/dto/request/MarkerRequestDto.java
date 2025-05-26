package com.ssafy.buyhouse.domain.estate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class MarkerRequestDto {
    private double minLat;
    private double maxLat;
    private double minLng;
    private double maxLng;
}
