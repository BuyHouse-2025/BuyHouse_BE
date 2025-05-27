package com.ssafy.buyhouse.domain.interest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "dongcodes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Dongcode {
    @Id
    @Column(name = "dong_code")
    private String dongCode;
    @Column(name = "sido_name")
    private String sido;
    @Column(name = "gugun_name")
    private String gugun;
    @Column(name = "dong_name")
    private String dong;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;

}
