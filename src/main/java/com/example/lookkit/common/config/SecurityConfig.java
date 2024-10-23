package com.example.lookkit.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) ->
//                authorize.requestMatchers("/**").permitAll() // 모든 페이지 허용
              authorize.requestMatchers("/", "/auth/**","/fail","/common/**","/main/**","/error/**","/fail/**","/css/**", "/js/**", "/images/**").permitAll() // 모두에게 허용되도록 설정
                      .requestMatchers("/mypage/**").authenticated() // 마이페이지 로그인회원만 접근가능
                      .anyRequest().authenticated() // 그 외의 모든 요청도 인증된 사용자만 접근 가능하도록 설정
        );
        http.formLogin((formLogin) -> formLogin
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/main")
                .failureUrl("/auth/login?error")
        );

        http.logout( logout -> logout
                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout") // 로그아웃 후 리디렉션 경로 설정. 디폴트는 요청페이지
                .invalidateHttpSession(true) // 세션 무효화하기
                .deleteCookies("JSESSIONID") // 인증 관련 쿠키 삭제
                .permitAll() // 누구나 로그아웃 요청 가능하도록 설정
         );
        return http.build();
    }
}