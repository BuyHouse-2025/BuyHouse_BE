package com.ssafy.buyhouse.domain.member.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.buyhouse.domain.estate.domain.OwnedHouse;
import com.ssafy.buyhouse.domain.interest.domain.Dongcode;
import com.ssafy.buyhouse.domain.wish.domain.WishHouse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pwd_question", referencedColumnName = "id")
    private PwdQuestion pwdQuestion;
    @Column(name = "pwd_answer")
    private String pwdAnswer;
    @Column(name = "cash")
    private Long cash;
    @Enumerated(EnumType.STRING)
    private UserType type;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "member", orphanRemoval = true)
    private List<OwnedHouse> ownedHouses = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "member", orphanRemoval = true)
    private List<WishHouse> wishHouses = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "member", orphanRemoval = true)
    private List<Dongcode> dongcodes = new ArrayList<>();


    public String getBirthDate() {
        return this.birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void CashConversion(int ownedPrice) {
        cash += ownedPrice;
    }
}
