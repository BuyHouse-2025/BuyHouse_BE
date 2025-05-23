package com.ssafy.buyhouse.domain.auth.service;

import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.domain.PrincipalDetail;
import com.ssafy.buyhouse.domain.member.domain.UserType;
import com.ssafy.buyhouse.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        Map<String, Object> attributes = user.getAttributes();
        UserType userType = findUserType(attributes);
        String id;
        String name;
        String email;

        if(userType == UserType.NAVER){
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            id = (String) response.get("id");
            name = (String) response.get("name");
            email = (String) response.get("email");
        }
        else if(userType == UserType.KAKAO){
            Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
            id = ((Long) attributes.get("id")).toString();
            name = (String) ((Map<String, Object>) response.get("profile")).get("nickname");
            email = (String) response.get("email");
        } else {
            name = null; email = null; id = null;
        }

        Optional<Member> byEmail = memberRepository.findByEmail(email);
        Member member = byEmail.orElseGet(() -> saveSocialMember(id, email, name, userType));

        return new PrincipalDetail(member, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes);
    }

    private UserType findUserType(Map<String, Object> attributes){
        boolean isNaver = attributes.containsKey("resultcode");
        if(isNaver) return UserType.NAVER;
        else return UserType.KAKAO;
    }

    public Member saveSocialMember(String id, String email, String name, UserType type) {
        Member newMember = Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .type(type)
                .cash(5000000000L)
                .build();
        return memberRepository.save(newMember);
    }


}