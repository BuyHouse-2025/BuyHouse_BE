package com.ssafy.buyhouse.domain.estate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDto {

    private String userId;
    private Long dealId;
}
