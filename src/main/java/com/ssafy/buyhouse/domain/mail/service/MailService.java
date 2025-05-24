package com.ssafy.buyhouse.domain.mail.service;

import com.ssafy.buyhouse.domain.member.dto.response.MemberFindIdResponse;
import com.ssafy.buyhouse.domain.member.dto.response.MemberFindPwdResponse;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public String findIdMail(MemberFindIdResponse findIdResponse) {
        Context context = new Context();
        context.setVariable("name", findIdResponse.name());
        context.setVariable("userId", findIdResponse.id());

        return templateEngine.process("emailId", context);
    }

    public String findPwdMail(MemberFindPwdResponse findPwdResponse) {
        Context context = new Context();
        context.setVariable("name", findPwdResponse.name());
        context.setVariable("password", findPwdResponse.Pwd());

        return templateEngine.process("emailPwd", context);
    }

    public void sendMimeMessage(String content, String title, String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일을 받을 수신자 설정
            mimeMessageHelper.setTo(email);
            // 메일의 제목 설정
            mimeMessageHelper.setSubject(title);

            // 메일의 내용 설정
            mimeMessageHelper.setText(content, true);

            javaMailSender.send(mimeMessage);

            log.info("메일 발송 성공!");
        } catch (Exception e) {
            log.info("메일 발송 실패!");
            throw new RuntimeException(e);
        }
    }

}
