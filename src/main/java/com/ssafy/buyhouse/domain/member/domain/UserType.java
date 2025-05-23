package com.ssafy.buyhouse.domain.member.domain;

public enum UserType {
    KAKAO("kakao"),
    NAVER("naver"),
    NORMAL("normal");

    private final String label;

    UserType(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
