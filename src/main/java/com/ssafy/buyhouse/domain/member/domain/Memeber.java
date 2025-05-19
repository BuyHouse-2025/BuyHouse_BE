package com.ssafy.buyhouse.domain.member.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "member")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memeber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "pwd_question")
    private int pwdQuestion;

    @Column(name = "pwd_answer")
    private int pwdAnswer;

    @Column(name = "cash")
    private int cash;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Memeber(String id, String password, String email, String name, String phoneNumber, int pwdAnswer, Role role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pwdAnswer = pwdAnswer;
        this.role = role;
    }

    public boolean isUser() {
        return Role.USER.equals(role);
    }
}
