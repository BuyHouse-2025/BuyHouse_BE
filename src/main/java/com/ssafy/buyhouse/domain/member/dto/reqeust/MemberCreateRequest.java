package com.ssafy.buyhouse.domain.member.dto.reqeust;

import com.ssafy.buyhouse.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.web.webauthn.api.PublicKeyCose;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {

    private String id;
    private String password;
    private String email;
    private String name;
    private Date birthday;
    private String phoneNumber;
    private int pwdQuestion;
    private String pwdAnswer;

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
