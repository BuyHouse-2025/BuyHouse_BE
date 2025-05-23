package com.ssafy.buyhouse.domain.member.dto.reqeust;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MemberUpdatePwdRequest(

        @NotBlank(message = "기존 비밀번호를 입력하세요")
        String passwordOrigin,

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(message = "새 비밀번호를 입력하세요")
        String passwordNew,

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        @NotBlank(message = "새 비밀번호를 한번 더 입력하세요")
        String passwordNewCheck
    ){
}
