package com.fastcampus.chap1clip1;

import com.fastcampus.chap1clip1.model.Animal;
import com.fastcampus.chap1clip1.producer.ClipProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chap1Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Clip1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(ClipProducer clipProducer) {
        return args -> {
//            clipProducer.async("clip4-listener", "Hello, Clip4 Listener.");
            clipProducer.async("clip4-animal", new Animal("puppy", 5 ));
            clipProducer.async("clip4-animal", new Animal("puppy", 15 ));
        };
    }

}
