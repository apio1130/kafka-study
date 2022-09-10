package com.fastcampus.chap3clip1;

import com.fastcampus.chap3clip1.service.KafkaManager;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chap3Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap3Clip1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaManager kafkaManager) {
        return args -> {
            kafkaManager.describeTopicConfigs();
        };
    }

}
