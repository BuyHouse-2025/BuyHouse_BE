package com.ssafy.buyhouse.domain.member.dto.reqeust;

import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record MemberCreateRequest (

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 2, max = 10, message = "아이디는 2자 이상 10자 이하로 입력해주세요.")
    String id,

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    String password,

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    String email,

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    String name,

    Date birthday,

    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    String phoneNumber,

    @NotBlank(message = "비밀번호 찾기 질문을 선택해주세요.")
    int pwdQuestion,

    @NotBlank(message = "비밀번호 찾기 답변을 선택해주세요.")
    String pwdAnswer
    ){

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .password(this.password)
                .email(this.email)
                .name(this.name)
                .birthDate(this.birthday)
                .phoneNumber(this.phoneNumber)
                .pwdQuestion(this.pwdQuestion)
                .pwdAnswer(this.pwdAnswer)
                .build();
    }
}
