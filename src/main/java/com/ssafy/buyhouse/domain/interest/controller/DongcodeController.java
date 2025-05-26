package com.ssafy.buyhouse.domain.interest.controller;

import com.ssafy.buyhouse.domain.interest.dto.response.CoordinateResponse;
import com.ssafy.buyhouse.domain.interest.dto.response.DongcodeResponse;
import com.ssafy.buyhouse.domain.interest.repository.DongcodeRepository;
import com.ssafy.buyhouse.domain.interest.service.KakaoMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dongcode")
public class DongcodeController {

    private final KakaoMapService kakaoMapService;

    private final DongcodeRepository dongcodeRepository;

    @GetMapping
    public List<DongcodeResponse> getAllDongcodes() {
        return dongcodeRepository.findAll().stream()
                .map(DongcodeResponse::from)
                .toList();
    }

    @GetMapping("/sido")
    public List<String> getSidoList() {
        return dongcodeRepository.findDistinctSido();
    }

    @GetMapping("/gugun")
    public List<String> getGugunList(@RequestParam String sido) {
        return dongcodeRepository.findDistinctGugunBySido(sido);
    }

    @GetMapping("/dong")
    public List<String> getDongList(@RequestParam String sido, @RequestParam String gugun) {
        return dongcodeRepository.findDongBySidoAndGugun(sido, gugun);
    }

    @GetMapping("/coordinate")
    public ResponseEntity<CoordinateResponse> getCoordinates(
            @RequestParam String sido,
            @RequestParam String gugun,
            @RequestParam String dong
    ) {
        String fullAddress = String.format("%s %s %s", sido, gugun, dong);
        CoordinateResponse coordinate = kakaoMapService.getCoordinateFromAddress(fullAddress);
        return ResponseEntity.ok(coordinate);
    }
}
