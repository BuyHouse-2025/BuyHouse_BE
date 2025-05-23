package com.ssafy.buyhouse.domain.auth.controller;

import com.ssafy.buyhouse.domain.member.dto.reqeust.LoginForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    //기본 로그인
/*    @GetMapping("login")
    public ResponseEntity<?> loginNormal(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult){

    }*/

    //카카오 로그인

    //네이버 로그인

    //로그아웃
}
