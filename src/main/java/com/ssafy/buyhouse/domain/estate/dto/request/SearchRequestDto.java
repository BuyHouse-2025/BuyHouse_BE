package com.ssafy.buyhouse.domain.estate.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    private String aptNm;
    private Integer minPrice;
    private Integer maxPrice;
    private Double minSquare;
    private Double maxSquare;

}
