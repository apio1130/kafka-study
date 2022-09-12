package com.fastcampus.chap3clip3.consumer;

import com.fastcampus.chap3clip3.model.Animal;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ClipConsumer {

    @KafkaListener(id = "clip3-animal-listener", topics = "clip3-animal", containerFactory = "kafkaJsonContainerFactory")
    public void listenAnimal(@Valid Animal animal) {
        System.out.println("animal = " + animal);
    }
}
