package com.ssafy.buyhouse.domain.member.dto.reqeust;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;
import java.time.LocalDate;

public record MemberUpdateRequest (

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    String name,

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday,

    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    String phoneNumber,

    Integer pwdQuestion,
    String pwdAnswer

){}
