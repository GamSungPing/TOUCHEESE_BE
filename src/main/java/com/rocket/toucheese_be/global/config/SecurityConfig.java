package com.rocket.toucheese_be.global.config;

import com.rocket.toucheese_be.global.security.jwt.CustomJwtAuthenticationEntryPoint;
import com.rocket.toucheese_be.global.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CORS 허용, CSRF 비활성화
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        // 세션 기반 인증 사용하지 않음 = STATELESS
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // httpBasic, httpFormLogin (폼 기반 로그인) 비활성화
        http.httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        // JWT 관련 필터 설정 및 인증 실패 시 예외 처리
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(customJwtAuthenticationEntryPoint))
            .authorizeHttpRequests(authorizeHttpRequests ->
                    authorizeHttpRequests
                            // 인증 없이 접근 가능한 url
                            .requestMatchers("/swagger-ui/**").permitAll() // Swagger UI 외부 접속 허용
                            .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).permitAll() // TODO: 삭제 예정
                            .requestMatchers(new AntPathRequestMatcher("/error")).permitAll()
                            // 이외의 모든 요청은 인증 정보 필요
                            .anyRequest().authenticated());

        // Http 요청이 비번 인증 전에 jwt 필터 먼저 거치도록
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
