package com.ssafy.buyhouse.domain.estate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "housedeals")
public class HouseDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_seq", referencedColumnName = "apt_seq")
    private HouseInfo houseInfo;

    @Column(name = "apt_dong")
    private String aptDong;

    @Column
    private String floor;

    @Column(name = "deal_year")
    private int dealYear;

    @Column(name = "deal_month")
    private int dealMonth;

    @Column(name = "deal_day")
    private int dealDay;

    @Column(name = "exclu_use_ar")
    private Double excluUseAr;

    @Column(name = "deal_amount")
    private String dealAmount;

}
