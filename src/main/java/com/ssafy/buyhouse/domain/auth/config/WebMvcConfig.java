package com.ssafy.buyhouse.domain.auth.config;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUserArgumentResolver;
import com.ssafy.buyhouse.domain.auth.util.TokenProvider;
import com.ssafy.buyhouse.domain.member.service.MemberService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    public WebMvcConfig(MemberService memberService, TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver(memberService, tokenProvider));
    }
}