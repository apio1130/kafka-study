package com.fastcampus.chap3clip4.configuration;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class ClipStreamsConfiguration {

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        return streamsBuilder.stream("clip4");
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(StreamsConfig.APPLICATION_ID_CONFIG, "clip4-streams-id");
        configs.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        configs.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        return new KafkaStreamsConfiguration(configs);
    }

}
