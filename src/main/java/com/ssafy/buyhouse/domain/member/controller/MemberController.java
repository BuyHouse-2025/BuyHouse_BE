package com.ssafy.buyhouse.domain.member.controller;

import com.ssafy.buyhouse.domain.member.dto.reqeust.MemberCreateRequest;
import com.ssafy.buyhouse.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;


    // 회원가입 정보저장
//    @PostMapping("/save")
//    public ResponseEntity<?> postMember(@RequestBody MemberCreateRequest MemberCreateRequest) {
//        String result = memberService.registerMember(MemberCreateRequest);
//        return ResponseEntity.ok().body(result);
//    }
}
