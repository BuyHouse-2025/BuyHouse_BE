package com.ssafy.buyhouse.domain.estate.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "house_detail_info")
public class HouseDetailInfo {

    @Id
    @Column(name = "apt_seq")
    private String aptSeq;

    @Column(name = "minArea")
    private double minArea;

    @Column(name = "maxArea")
    private double maxArea;

    @Column(name = "representativeArea")
    private double representativeArea;

    @Column
    private int floorAreaRatio;

    @Column(name = "PARKING")
    private int parking;

    @Column(name = "BUS")
    private int bus;

    @Column(name = "METRO")
    private int metro;

    @Column(name = "HOSPITAL")
    private int hospital;

    @Column(name = "MART")
    private int mart;

    @Column(name = "CONVENIENCE")
    private int convenience;

    @Column(name = "INFANT")
    private int infant;

    @Column(name = "PRESCHOOL")
    private int preschool;

    @Column(name = "naver_minDeal")
    private int naverMinDeal;

    @Column(name = "naver_maxDeal")
    private int naverMaxDeal;

    @Column
    private int build;
    
}
