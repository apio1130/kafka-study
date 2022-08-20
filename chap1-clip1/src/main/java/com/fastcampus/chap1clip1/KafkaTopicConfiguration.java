package com.fastcampus.chap1clip1;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic clip2() {
        return TopicBuilder.name("clip2").build();
    }

}
