package com.jhw.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    // 스프링 부트의 자동 설정, 스프링 Bean의 읽기와 생성을 모두 자동 설정
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
