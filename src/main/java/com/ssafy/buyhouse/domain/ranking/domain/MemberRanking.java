package com.ssafy.buyhouse.domain.ranking.domain;

import com.ssafy.buyhouse.domain.common.BaseTime;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@Table(name = "member_ranking")
public class MemberRanking extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
    @Column(name = "total_asset")
    private Long totalAsset;
    @Column(name = "ranking")
    private Integer ranking;
    @Column(name = "roi")
    private Double roi;

    public MemberRanking(Member member, Long totalAsset, int i, Double roi) {
        this.member = member;
        this.totalAsset = totalAsset;
        this.ranking = i;
        this.roi = roi;
    }

}
