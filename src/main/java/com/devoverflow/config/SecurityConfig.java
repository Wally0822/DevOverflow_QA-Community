package com.devoverflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // 비밀번호 암호화 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // 회원가입 API, 정적 리소스 허용
                .requestMatchers("/signup.html", "/api/signup", "/css/**", "/js/**").permitAll()
                // 그 외는 인증 필요
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults()); // 기본 로그인 폼

        return http.build();
    }
}
