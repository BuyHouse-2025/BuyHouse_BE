package com.ssafy.buyhouse.domain.auth.util;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.domain.PrincipalDetail;
import com.ssafy.buyhouse.domain.member.service.MemberService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import javax.naming.AuthenticationException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class TokenProvider {
    static String secretKey = JwtConstants.key;
    private final MemberService memberService;


    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    public static String generateToken(Member member, int validTime) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(TokenProvider.secretKey.getBytes(StandardCharsets.UTF_8));
        } catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return Jwts.builder()
                .setHeader(Map.of("typ","JWT"))
                .setSubject(member.getId().toString())
                .claim("email", member.getEmail())
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(validTime).toInstant()))
                .signWith(key)
                .compact();
    }


    public Authentication
    getAuthentication(String token) throws AuthenticationException {
        String tokenFromHeader = getTokenFromHeader(token);
        String claims = getClaims(tokenFromHeader);
        if(claims == null) throw new AuthenticationException("토큰값이 잘못되었습니다");
        Member member = memberService.findMemberById(String.valueOf(claims));

        PrincipalDetail principalDetail = new PrincipalDetail(member);
        return new UsernamePasswordAuthenticationToken(principalDetail, "", principalDetail.getAuthorities());
    }

    public static boolean validateToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(TokenProvider.secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 토큰이 유효하지 않은 경우
        }
    }

    public static boolean isExpired(String token) {
        try {
            validateToken(token);
        } catch (Exception e) {
            return (e instanceof ExpiredJwtException);
        }
        return false;
    }

    public static long tokenRemainTime(Integer expTime) {
        Date expDate = new Date((long) expTime * (1000));
        long remainMs = expDate.getTime() - System.currentTimeMillis();
        return remainMs / (1000 * 60);
    }

    public static String getClaims(String jwt) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

        }
        catch (JwtException e){
            return null;
        }
    }

    public static ResponseCookie createCookie(String refreshToken) { // 수정
        String cookieName = JwtConstants.REFRESH;

        ResponseCookie cookie = ResponseCookie.from(cookieName, refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(Duration.ofDays(1))
                .sameSite("Secure")
                .build();

        return cookie;
    }

}