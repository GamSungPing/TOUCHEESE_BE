package com.rocket.toucheese_be.global.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Getter
    public static int basePageSize = 50;

    @Getter
    public static int reviewPageSize = 24;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
