package com.ssafy.buyhouse.domain.member.service;

import com.ssafy.buyhouse.domain.member.domain.PwdQuestion;
import com.ssafy.buyhouse.domain.member.repository.PwdQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PwdQuestionService {
    private final PwdQuestionRepository pwdQuestionRepository;
    public List<PwdQuestion> findAll() {
        return pwdQuestionRepository.findAll();
    }
}
