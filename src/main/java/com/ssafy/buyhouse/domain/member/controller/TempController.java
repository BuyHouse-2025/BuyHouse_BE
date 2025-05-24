package com.ssafy.buyhouse.domain.member.controller;

import com.ssafy.buyhouse.domain.auth.annotation.LoginUser;
import com.ssafy.buyhouse.domain.mail.service.MailService;
import com.ssafy.buyhouse.domain.member.domain.Member;
import com.ssafy.buyhouse.domain.member.dto.reqeust.LoginForm;
import com.ssafy.buyhouse.domain.member.dto.reqeust.MemberCreateRequest;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class TempController {
    private final MailService mailService;
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

    @ResponseBody
    @GetMapping("/mailTest")
    public String mailTest(@LoginUser Member member){
        String mail = mailService.findIdMail(new MemberFindIdResponse(member.getName(), member.getId()));
        mailService.sendMimeMessage(mail, "[집사] 아이디 찾기 결과입니다.", member.getEmail());
        return "send mail";
    }

}
