package com.ssafy.buyhouse.domain.auth.controller;

import com.ssafy.buyhouse.domain.auth.util.JwtConstants;
import com.ssafy.buyhouse.domain.auth.util.TokenProvider;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> JwtConstants.REFRESH.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if (refreshToken == null || !TokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 리프레시 토큰입니다.");
        }

        String memberId = TokenProvider.getClaims(refreshToken);
        if (memberId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰에서 사용자 정보를 가져올 수 없습니다.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        String newAccessToken = TokenProvider.generateToken(member, JwtConstants.ACCESS_EXP_TIME_MINUTES);
        String newRefreshToken = TokenProvider.generateToken(member, JwtConstants.REFRESH_EXP_TIME_MINUTES);

        response.addHeader(JwtConstants.ACCESS, JwtConstants.JWT_TYPE + newAccessToken);
        response.addHeader("Set-Cookie", TokenProvider.createCookie(newRefreshToken).toString());

        return ResponseEntity.ok().body("Access & Refresh Token 재발급 완료");
    }

}
