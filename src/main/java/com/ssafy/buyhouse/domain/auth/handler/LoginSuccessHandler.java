package com.ssafy.buyhouse.domain.auth.handler;

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

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${app.redirect.uri}")
    String uri;

    private final TokenProvider tokenProvider;

    public LoginSuccessHandler(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("LoginSuccessHandler 호출 성공");

        PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
        Member member = principal.getUser();

        String accessToken = tokenProvider.generateToken(member, JwtConstants.ACCESS_EXP_TIME_MINUTES);
        String refreshToken = tokenProvider.generateToken(member, JwtConstants.REFRESH_EXP_TIME_MINUTES);


        response.addHeader(JwtConstants.ACCESS, JwtConstants.JWT_TYPE + accessToken);
        response.addHeader("Set-Cookie", tokenProvider.createCookie(refreshToken).toString());

        UserType type = member.getType();
        String redirectURL;
        if(type == UserType.NORMAL){
            redirectURL = UriComponentsBuilder.fromUriString(uri + "/auth/" + type.getLabel())
                    .queryParam(JwtConstants.ACCESS, accessToken)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
        }
        else{
            redirectURL = UriComponentsBuilder.fromUriString(uri + "/auth/" + type.getLabel())
                    .queryParam(JwtConstants.ACCESS, accessToken)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
        }

        System.out.println(redirectURL);
        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, redirectURL);

    }
}
