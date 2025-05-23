package com.ssafy.buyhouse.domain.auth.annotation;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.auth.util.TokenProvider;
import com.ssafy.buyhouse.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.naming.AuthenticationException;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    public LoginUserArgumentResolver(MemberService memberService, TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean assignableFrom = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginUserAnnotation && assignableFrom;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String accessToken = request.getHeader("Authorization");

        if(accessToken == null) throw new AuthenticationException("인증에 실패하였습니다.");
        String claims = tokenProvider.getClaims(tokenProvider.getTokenFromHeader(accessToken));
        if(claims == null) throw new AuthenticationException("인증에 실패하였습니다.");
        return memberService.findMemberById(String.valueOf(claims));
    }
}