package com.rocket.toucheese_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 스케줄링 활성화
public class ToucheeseBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToucheeseBeApplication.class, args);
    }

}
