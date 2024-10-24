package com.example.lookkit.common.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Random;

@Controller
@Setter
public class MailController {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/mailsender")
    @ResponseBody
    public String send(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        try {
            // MIME 메일 생성
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 발신자 이메일과 표시할 이름 설정
            messageHelper.setFrom(new InternetAddress("dhkdwk9430@gmail.com", "Lookkit"));

            // 수신자 이메일 설정
            messageHelper.setTo(email);

            // 메일 제목 및 내용 설정
            messageHelper.setSubject("[Lookkit] 이메일 인증");
            String randomCode = generateRandomCode();  // 인증코드 생성
            messageHelper.setText("인증 코드: " + randomCode);

            // 메일 발송
            javaMailSender.send(mimeMessage);
            return randomCode;  // 인증 코드 반환

        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
            return "메일 발송에 실패했습니다.";
        }

    }
    //인증번호 랜덤값 6자리 생성
    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6자리 난수 생성 (100000 ~ 999999)
        return String.valueOf(code);
    }



}