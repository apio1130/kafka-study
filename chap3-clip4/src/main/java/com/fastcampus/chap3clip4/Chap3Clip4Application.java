package com.fastcampus.chap3clip4;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Chap3Clip4Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap3Clip4Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate) {
        return args -> {
            while (true) {
                kafkaTemplate.send("clip4", "Hello, Clip4");
                Thread.sleep(1_000);
            }
        };
    }

}
