package com.ssafy.buyhouse.domain.interest.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class KakaoResponse {
    private List<KakaoDocument> documents;
}