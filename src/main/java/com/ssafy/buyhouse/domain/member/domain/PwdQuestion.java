package com.ssafy.buyhouse.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "password_question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class PwdQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "question")
    private String question;
}
