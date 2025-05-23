package com.ssafy.buyhouse.domain.member.controller;

import com.ssafy.buyhouse.domain.member.dto.reqeust.LoginForm;
import com.ssafy.buyhouse.domain.member.dto.reqeust.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class TempController {
    @GetMapping("/")
    public String homeLogin() {
        return "home";
    }

    @GetMapping("/login")
    public String homeLoginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @GetMapping("/join")
    public String homeJoinForm(@ModelAttribute("MemberCreateRequest") MemberCreateRequest form) {
        return "members/addMemberForm";
    }

}
