package com.ssafy.buyhouse.domain.member.dto.reqeust;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public record MemberFindPwdRequest(

        @NotBlank(message = "아이디를 입력해주세요.")
        @Size(min = 2, max = 10, message = "아이디는 2자 이상 10자 이하로 입력해주세요.")
        String id,

        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        String email,

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        String name,

        @NotBlank(message = "생년월일을 입력해주세요.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthday,

        @NotBlank(message = "전화번호를 입력해주세요.")
        @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
        String phoneNumber,

        @NotBlank(message = "비밀번호 찾기 질문을 선택해주세요.")
        int pwdQuestion,

        @NotBlank(message = "비밀번호 찾기 답변을 선택해주세요.")
        String pwdAnswer
    ){
        public String getBirthDate() {
                return this.birthday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
}
