package com.ssafy.buyhouse.domain.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_USER"), USER("ROLE_MANAGER");
    private final String role;
}
