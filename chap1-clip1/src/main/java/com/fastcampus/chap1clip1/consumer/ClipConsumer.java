package com.fastcampus.chap1clip1.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip4-listener-id", topics = "clip4-listener")
    public void listen(String message) {
        System.out.println("Listener message=" + message);
    }
}
