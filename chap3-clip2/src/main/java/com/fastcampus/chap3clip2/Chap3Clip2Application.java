package com.fastcampus.chap3clip2;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chap3Clip2Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap3Clip2Application.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {

        };
    }

}
