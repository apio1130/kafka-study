package com.fastcampus.chap1clip1;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
public class Chap1Clip1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap1Clip1Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(AdminClient adminClient) {
        return args -> {
            // topic 정보 확인
            Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
            for (String topicName : topics.keySet()) {
                TopicListing topicListing = topics.get(topicName);
                System.out.println(topicListing);

                Map<String, TopicDescription> description = adminClient.describeTopics(Collections.singleton(topicName)).all().get();
                System.out.println(description);

                if (!topicListing.isInternal()) {
                    // topic 삭제
                    adminClient.deleteTopics(Collections.singleton(topicName));
                }
            }
        };
    }

}
