package com.ssafy.buyhouse.domain.estate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Houseinfo {
    private String kaptCode;
    private String kaptName;
    private String bjdCode;
    private String kaptAddr;
    private String doroJuso;
    private String codeHeatNm;
    private double kaptTarea;
    private int kaptTopFloor;
    private int kaptDongCnt;
    private int kaptdaCnt;
    private String kaptBcompany;
    private String codeAptNm;
    private double privArea;


}
