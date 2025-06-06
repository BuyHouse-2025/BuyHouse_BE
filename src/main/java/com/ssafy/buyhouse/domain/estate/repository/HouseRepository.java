package com.ssafy.buyhouse.domain.estate.repository;

import com.ssafy.buyhouse.domain.estate.domain.HouseInfo;
import com.ssafy.buyhouse.domain.estate.dto.request.SearchRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<HouseInfo, String> {


    // 마커
    List<HouseInfo> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLng, double maxLng
    );

    // 검색 기능
    @Query("""
        SELECT h
          FROM HouseInfo h
         WHERE (:aptNm     IS NULL OR h.aptNm LIKE CONCAT('%', :aptNm, '%'))
           AND ((:minPrice IS NULL AND :maxPrice IS NULL)
             OR (:minPrice IS NOT NULL AND :maxPrice IS NOT NULL
                 AND h.detailInfo.naverMinDeal >= :minPrice
                 AND h.detailInfo.naverMaxDeal <= :maxPrice))
           AND (:minSquare IS NULL
             OR (h.detailInfo.minArea >= (:minSquare * 3.3058)
              AND h.detailInfo.maxArea <= (:maxSquare * 3.3058)))
    """)
    Page<HouseInfo> findBySearchInfo(
            @Param("aptNm")     String aptNm,
            @Param("minPrice")  Integer minPrice,
            @Param("maxPrice")  Integer maxPrice,
            @Param("minSquare") Double  minSquare,
            @Param("maxSquare") Double  maxSquare,
            Pageable pageable
    );
}
