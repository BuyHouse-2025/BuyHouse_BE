package com.ssafy.buyhouse.domain.interest.service;

import com.ssafy.buyhouse.domain.interest.dto.response.CoordinateResponse;
import com.ssafy.buyhouse.domain.interest.dto.response.KakaoDocument;
import com.ssafy.buyhouse.domain.interest.dto.response.KakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders; //


@Service
@RequiredArgsConstructor
public class KakaoMapService {

    @Value("${kakao.client-id}")
    private String kakaoApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public CoordinateResponse getCoordinateFromAddress(String address) {
        String url = "https://dapi.kakao.com/v2/local/search/address.json";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query", address);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<KakaoResponse> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                KakaoResponse.class
        );

        KakaoResponse kakao = response.getBody();
        if (kakao != null && !kakao.getDocuments().isEmpty()) {
            KakaoDocument doc = kakao.getDocuments().get(0);
            return new CoordinateResponse(
                    Double.parseDouble(doc.getY()),
                    Double.parseDouble(doc.getX())
            );
        }

        throw new IllegalArgumentException("좌표를 찾을 수 없습니다.");
    }
}
