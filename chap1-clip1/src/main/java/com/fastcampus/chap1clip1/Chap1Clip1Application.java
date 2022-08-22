package com.fastcampus.chap1clip1;

import com.fastcampus.chap1clip1.producer.ClipProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

@SpringBootApplication
public class Chap1Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Clip1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer,
                                    KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer) {
        return args -> {
            clipProducer.async("clip4", "Hello, Clip4 Container.");

            kafkaMessageListenerContainer.start();
            Thread.sleep(1_000L);

            System.out.println("--- pause ---");
            kafkaMessageListenerContainer.pause();
            Thread.sleep(5_000L);

            clipProducer.async("clip4", "Hello, Secondly Clip4 Container.");

            System.out.println("--- resume ---");
            kafkaMessageListenerContainer.resume();
            Thread.sleep(1_000L);

            System.out.println("--- stop ---");
            kafkaMessageListenerContainer.stop();
        };
    }

}
