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
    private String floorAreaRatio;

    @Column
    private String minDeal;

    @Column
    private String maxDeal;

    @Column
    private String PARKING;

    @Column
    private String BUS;

    @Column
    private String METRO;

    @Column
    private int HOSPITAL;

    @Column
    private String MART;

    @Column
    private String CONVENIENCE;

    @Column
    private String INFANT;

    @Column
    private String PRESCHOOL;

    @Column(name = "PRI_SCHOOL")
    private String PRISCHOOL;

    @Column(name = "PUB_SCHOOL")
    private String PUBSCHOOL;

    @Column
    private Integer predictPrice;
}
