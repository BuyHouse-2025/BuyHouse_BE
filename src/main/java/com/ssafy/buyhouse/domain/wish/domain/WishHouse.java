package com.ssafy.buyhouse.domain.wish.domain;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_seq", referencedColumnName = "apt_seq", nullable = false)
    private HouseInfo houseInfo;
}
