package com.ssafy.buyhouse.domain.interest.repository;

import com.ssafy.buyhouse.domain.interest.domain.Dongcode;
import com.ssafy.buyhouse.domain.interest.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DongcodeRepository extends JpaRepository<Dongcode, String> {

    @Query("SELECT DISTINCT d.sido FROM Dongcode d ORDER BY d.sido")
    List<String> findDistinctSido();

    @Query("SELECT DISTINCT d.gugun FROM Dongcode d WHERE d.sido = :sido ORDER BY d.gugun")
    List<String> findDistinctGugunBySido(@Param("sido") String sido);

    @Query("SELECT d.dong FROM Dongcode d WHERE d.sido = :sido AND d.gugun = :gugun ORDER BY d.dong")
    List<String> findDongBySidoAndGugun(@Param("sido") String sido, @Param("gugun") String gugun);
}

