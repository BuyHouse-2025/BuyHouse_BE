package com.ssafy.buyhouse.domain.interest.service;

import com.ssafy.buyhouse.domain.interest.domain.Dongcode;
import com.ssafy.buyhouse.domain.interest.domain.Interest;
import com.ssafy.buyhouse.domain.interest.dto.request.DeleteInterestRequest;
import com.ssafy.buyhouse.domain.interest.dto.request.DeleteInterestsRequest;
import com.ssafy.buyhouse.domain.interest.dto.response.InterestResponse;
import com.ssafy.buyhouse.domain.interest.dto.response.InterestsResponse;
import com.ssafy.buyhouse.domain.interest.repository.DongcodeRepository;
import com.ssafy.buyhouse.domain.interest.repository.InterestRepository;
import com.ssafy.buyhouse.domain.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final DongcodeRepository dongcodeRepository;

    public Interest addInterest(String dongCodeId, Member member) {
        Dongcode dongcode = dongcodeRepository.findById(dongCodeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 동코드입니다: " + dongCodeId));

        if(interestRepository.existsByDongcodeAndMember(dongcode, member))
            throw new IllegalArgumentException("이미 관심 지역으로 등록한 동입니다");

        return interestRepository.save(new Interest(member, dongcode));
    }

    @Transactional
    public void deleteInterest(DeleteInterestRequest deleteInterest, Member member) {
        Dongcode dong = dongcodeRepository.findById(deleteInterest.dongcode()).orElseThrow(() -> new IllegalArgumentException("동코드가 존재하지 않습니다"));
        Interest interest = interestRepository.findByMemberAndDongcode(member, dong)
                .orElseThrow(() -> new IllegalArgumentException("해당 관심지역이 없습니다."));

        if (interest.getMember().equals(member)) {
            interestRepository.deleteById(Long.valueOf(interest.getId()));
        } else {
            throw new IllegalArgumentException("본인의 관심지역만 삭제할 수 있습니다.");
        }

    }


    public InterestsResponse findAllByMember(Member member) {
        List<Interest> interests = interestRepository.findByMember(member);
        return InterestsResponse.from(interests);
    }

    public InterestResponse findById(Integer interestId, Member member) throws IllegalAccessException {
        Interest interest = interestRepository.findById(Long.valueOf(interestId))
                .orElseThrow(() -> new IllegalArgumentException("해당 관심지역이 없습니다"));
        if(!interest.getMember().getId().equals(member.getId())) throw new IllegalAccessException("본인의 관심지역만 조회할 수 있습니다.");
        return InterestResponse.from(interest);
    }
}
