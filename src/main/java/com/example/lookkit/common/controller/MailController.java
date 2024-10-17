package com.example.lookkit.common.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Random;

@Controller
@Setter
public class MailController {

    @Autowired
    private MailSender mailSender;

    @PostMapping("/mailsender")
    @ResponseBody
    public String send(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();
        mailMessage.setFrom("dhkdwk9430@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("[Lookkit] 이메일인증");
        String randomCode = generateRandomCode(); // 인증코드
        mailMessage.setText("인증 코드: " + randomCode);

        try {
            mailSender.send(mailMessage);

        } catch (Exception e) {
            System.out.println("예외발생:"+e.getMessage());
        }
        return randomCode; //인증코드를 반환

    }
    //인증번호 랜덤값 6자리 생성
    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6자리 난수 생성 (100000 ~ 999999)
        return String.valueOf(code);
    }



}