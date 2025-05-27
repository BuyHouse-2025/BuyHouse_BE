package com.ssafy.buyhouse.domain.model.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ModelService {
    private final RestTemplate rt;
    @Value("${ai.api.base-url}") String baseUrl;

    public ModelService(RestTemplate rt) {
        this.rt = rt;
    }

    public double[] predict(String aptSeq, double[] features) {
        Object[] objects = new Object[21];
        for (int i = 1; i <= 20; i++) {
            objects[i] = features[i-1];
        }
        objects[0] = aptSeq;
        List<Object[]> obj = new ArrayList<>();
        obj.add(objects);

        Map<String,Object> body = Map.of("instances", obj);

        for (Map.Entry<String, Object> entry : body.entrySet()) {
            //System.out.println(entry.getKey() + " " + entry.getValue());

        }
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
