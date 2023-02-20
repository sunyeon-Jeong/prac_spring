package com.sparta.hanghaeblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // 스프링 어플리케이션 선언
@EnableJpaAuditing // 인스턴스 생성 or 수정 -> 변화를 자동으로 감지하여 일시를 저장함
public class HanghaeblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanghaeblogApplication.class, args);
    }
}