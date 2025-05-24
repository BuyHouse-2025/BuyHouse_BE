package com.ssafy.buyhouse.domain.member.repository;

import com.ssafy.buyhouse.domain.member.domain.PwdQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PwdQuestionRepository extends JpaRepository<PwdQuestion,String> {
    public Optional<PwdQuestion> findById(Integer Id);
}
