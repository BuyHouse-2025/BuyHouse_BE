package com.ssafy.buyhouse.domain.estate.domain;

import com.ssafy.buyhouse.domain.common.BaseTime;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OwnedHouse extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_seq", referencedColumnName = "apt_seq", nullable = false)
    private HouseInfo houseInfo;
    
    @Column(name = "use_area")
    private int useArea;  // 평형

    @Column(name = "floor")
    private int floor;  // 층수
    
    @Column(nullable = false)
    private int ownedPrice; // 구매 가격

    @Column(nullable = false)
    private int currentPrice; // 현재 가격

}
