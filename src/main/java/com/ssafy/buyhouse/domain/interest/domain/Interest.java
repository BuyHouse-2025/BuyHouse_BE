package com.ssafy.buyhouse.domain.interest.domain;

import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "interested_city")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @JoinColumn(name = "dong_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dongcode dongcode;

    public Interest(Member member, Dongcode dongcode) {
        this.member = member;
        this.dongcode = dongcode;
    }
}
