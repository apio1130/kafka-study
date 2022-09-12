package com.fastcampus.chap3clip3.consumer;

import com.fastcampus.chap3clip3.model.Animal;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip3-listener-id", topics = "clip3-listener")
    public void listen(String message,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset,
                       ConsumerRecordMetadata metadata) {
        System.out.println("Listener. offset=" + metadata.offset() +
                ", partition=" + partition +
                ", timestamp=" + new Date(timestamp) +
                ", message=" + message);
    }

    @KafkaListener(id = "clip3-animal-listener", topics = "clip3-animal", containerFactory = "kafkaJsonContainerFactory")
    public void listenAnimal(@Valid Animal animal) {
        System.out.println("animal = " + animal);
    }
}
