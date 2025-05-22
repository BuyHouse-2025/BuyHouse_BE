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
    @Column
    private String id;
    @Column
    private String password;
    @Column
    private String email;
    @Column
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
    @Column
    private BigInteger cash;


}
