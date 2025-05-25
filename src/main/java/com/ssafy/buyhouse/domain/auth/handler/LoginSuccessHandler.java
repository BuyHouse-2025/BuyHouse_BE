package com.ssafy.buyhouse.domain.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.buyhouse.domain.auth.util.JwtConstants;
import com.ssafy.buyhouse.domain.auth.util.TokenProvider;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.domain.PrincipalDetail;
import com.ssafy.buyhouse.domain.member.domain.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${app.redirect.uri}")
    String uri;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("LoginSuccessHandler 호출 성공");

        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        Member member = principal.getUser();

        String accessToken = TokenProvider.generateToken(member, JwtConstants.ACCESS_EXP_TIME_MINUTES);
        String refreshToken = TokenProvider.generateToken(member, JwtConstants.REFRESH_EXP_TIME_MINUTES);


        response.addHeader(JwtConstants.JWT_HEADER, JwtConstants.JWT_TYPE + accessToken);
        response.addHeader("Set-Cookie", TokenProvider.createCookie(refreshToken).toString());

        UserType type = member.getType();
        String redirectURL;
        System.out.println(type);
        if (type == UserType.NORMAL) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        } else {
            redirectURL = UriComponentsBuilder.fromUriString(uri)
                    .queryParam(JwtConstants.ACCESS, accessToken)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
            clearAuthenticationAttributes(request);
            getRedirectStrategy().sendRedirect(request, response, redirectURL);
        }




    }
}
