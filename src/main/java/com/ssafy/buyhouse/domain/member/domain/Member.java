package com.ssafy.buyhouse.domain.member.domain;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Member {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "pwd_question")
    private Integer pwdQuestion;
    @Column(name = "pwd_answer")
    private String pwdAnswer;
    @Column(name = "cash")
    private Long cash;
    @Enumerated(EnumType.STRING)
    private UserType type;


}
