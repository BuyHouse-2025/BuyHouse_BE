package com.ssafy.buyhouse.domain.estate.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Table(name = "houseinfos")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {

    @Id
    @Column(name = "apt_seq")
    private String aptSeq;

    @Column(name = "sgg_cd")
    private String ssgCd;

    @Column(name = "umd_cd")
    private String umdCd;

    @Column(name = "umd_nm")
    private String umdNm;

    @Column(name = "jibun")
    private String jibun;

    @Column(name = "road_nm_sgg_cd")
    private String roadNmSggCd;

    @Column(name = "road_nm")
    private String roadNm;

    @Column(name = "road_nm_bonbun")
    private String roadNmBonbun;

    @Column(name = "road_nm_bubun")
    private String roadNmBubun;

    @Column(name = "apt_nm")
    private String aptNm;

    @Column(name = "build_year")
    private int buildYear;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @OneToOne(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @PrimaryKeyJoinColumn(name = "apt_seq")
    private HouseDetailInfo detailInfo;

    @OneToMany(mappedBy = "houseInfo", fetch = FetchType.LAZY)
    private List<HouseDeal> deals = new ArrayList<>();

}
