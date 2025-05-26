package com.ssafy.buyhouse.domain.interest.service;

import com.ssafy.buyhouse.domain.interest.domain.Dongcode;
import com.ssafy.buyhouse.domain.interest.domain.Interest;
import com.ssafy.buyhouse.domain.interest.dto.request.DeleteInterestsRequest;
import com.ssafy.buyhouse.domain.interest.dto.response.InterestsResponse;
import com.ssafy.buyhouse.domain.interest.repository.DongcodeRepository;
import com.ssafy.buyhouse.domain.interest.repository.InterestRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final DongcodeRepository dongcodeRepository;

    public Interest addInterest(Integer dongCodeId, Member member) {
        Dongcode dongcode = dongcodeRepository.findById(Long.valueOf(dongCodeId)).orElseThrow();
        return interestRepository.save(new Interest(member, dongcode));
    }

/*    public void deleteInterests(DeleteInterestsRequest deleteInterests, Member member) {

    }

    public InterestsResponse findAllByMember(Member member) {
    }

    public InterestsResponse findById(Integer interestId, Member member) {
    }*/
}
