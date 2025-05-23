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
public class HouseDetailInfo {

    @Id
    @Column(name = "apt_seq")
    private String aptSeq;

    @Column(name = "minArea", precision = 10, scale = 2)
    private BigDecimal minArea;

    @Column(name = "maxArea", precision = 10, scale = 2)
    private BigDecimal maxArea;

    @Column(name = "representativeArea", precision = 10, scale = 2)
    private BigDecimal representativeArea;

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

    @Column(name = "PRI_SCHOOL")
    private int priSchool;

    @Column(name = "PUB_SCHOOL")
    private int pubSchool;

    @Column(name = "predict_price")
    private int predictPrice;
}
