package com.ssafy.buyhouse.domain.member.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrincipalDetail implements OAuth2User, UserDetails {
    private Member member;
    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;
    public PrincipalDetail(Member member, Collection<? extends GrantedAuthority> authorities){
        this.member = member;
        this.authorities = authorities;

    }
    public PrincipalDetail(Member member, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes){
        this.member = member;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    public PrincipalDetail(Member member) {
        this.member = member;
        this.authorities = Arrays.asList(() -> "ROLE_USER");
    }

    public Map<String, Object> getMemberInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", member.getName());
        info.put("email", member.getEmail());
        info.put("id", member.getId());
        return info;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(authorities.isEmpty());
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return member.getName();
    }

    public String getId() {
        return member.getId();
    }

    public Member getUser() {
        return member;
    }
}