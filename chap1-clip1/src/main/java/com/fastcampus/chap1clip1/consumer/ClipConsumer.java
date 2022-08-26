package com.fastcampus.chap1clip1.consumer;

import org.apache.kafka.clients.consumer.internals.ConsumerMetadata;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip4-listener-id", topics = "clip4-listener")
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
}
