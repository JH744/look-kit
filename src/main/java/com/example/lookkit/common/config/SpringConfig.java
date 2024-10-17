package com.example.lookkit.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class SpringConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSenderImpl javaMailSender() {

        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setHost("smtp.gmail.com");
        jms.setPort(587);
        jms.setUsername("dhkdwk9430"); // G메일 계정명
        jms.setPassword("dmxx uqru kfae svsy"); // 발급받은 앱비밀번호
        jms.setDefaultEncoding("UTF-8");

        Properties prop = new Properties();
        prop.put("mail.smtp.starttls.enable",true);
        prop.put("mail.smtp.auth",true);
        prop.put("mail.smtp.ssl.checkserveridentity",true);
        prop.put("mail.smtp.ssl.trust","*");
        prop.put("mail.smtp.ssl.protocols","TLSv1.2");
        jms.setJavaMailProperties(prop);

        return jms;
    }





}
