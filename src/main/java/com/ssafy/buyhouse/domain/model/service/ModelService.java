package com.ssafy.buyhouse.domain.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ModelService {
    private final RestTemplate rt;
    @Value("${ai.api.base-url}") String baseUrl;

    public ModelService(RestTemplate rt) {
        this.rt = rt;
    }

    public double[] predict(double[] features) {
        Map<String,Object> body = Map.of("instances", List.of(features));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,Object>> req = new HttpEntity<>(body, headers);

        ResponseEntity<Map> resp = rt.postForEntity(
                baseUrl + "/predict", req, Map.class);
        if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody()==null) {
            throw new RuntimeException("AI 호출 실패: " + resp.getStatusCode());
        }

        @SuppressWarnings("unchecked")
        List<Double> preds = (List<Double>) resp.getBody().get("predictions");
        return preds.stream().mapToDouble(Double::doubleValue).toArray();
    }
}
