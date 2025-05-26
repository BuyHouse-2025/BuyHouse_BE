package com.ssafy.buyhouse.domain.interest.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.interest.domain.Interest;
import com.ssafy.buyhouse.domain.interest.dto.request.DeleteInterestsRequest;
import com.ssafy.buyhouse.domain.interest.dto.response.InterestsResponse;
import com.ssafy.buyhouse.domain.interest.service.InterestService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/interest")
public class InterestController {

    private final InterestService interestService;

    // 관심지역 추가
    @PostMapping("/{dongCode}")
    public ResponseEntity<?> addInterestedCity(@LoginUser Member member, @RequestParam("dongCode") Integer dongCodeId){
        Interest interest = interestService.addInterest(dongCodeId, member);
        return ResponseEntity.ok().body(interest);
    }

    // 관심지역 삭제
    @DeleteMapping()
    public ResponseEntity<?> deleteInterestedCities(@LoginUser Member member, @RequestBody DeleteInterestsRequest deleteInterests){
        interestService.deleteInterests(deleteInterests, member);
        return ResponseEntity.ok().body(null);
    }

    // 관심지역 조회 (전체 by user)
    @GetMapping
    public ResponseEntity<?> getAllInterestedCities(@LoginUser Member member){
        InterestsResponse interests = interestService.findAllByMember(member);
        return ResponseEntity.ok().body(interests);
    }

    // 관심지역 조회 (by id)
    @GetMapping("/{interestId}")
    public ResponseEntity<?> getInterestedCity(@LoginUser Member member, @RequestParam("interestId") Integer interestId){
        InterestsResponse interests = interestService.findById(interestId, member);
        return ResponseEntity.ok().body(interests);
    }
}
