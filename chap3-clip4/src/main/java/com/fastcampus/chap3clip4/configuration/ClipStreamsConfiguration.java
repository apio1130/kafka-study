package com.fastcampus.chap3clip4.configuration;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
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
        KStream<String, String> stream = streamsBuilder.stream("clip4");
//        stream.peek((key, value) -> System.out.println("Stream. message=" + value))
//                .map((key, value) -> KeyValue.pair(key, "Hello, Listener")) // 메시지 변형
//                .to("clip4-to"); // 다른 토픽으로 전달
        stream.groupBy((key, value) -> value)
                .count()
                .toStream()
                .peek((key, value) -> System.out.println("key=" + key + ", value=" + value))
        ;

        return stream;
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(StreamsConfig.APPLICATION_ID_CONFIG, "clip4-streams-id");
        configs.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        configs.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        configs.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, "2");  // 여러 쓰레드에서 실행하기 위한 설정
//        configs.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.AT_LEAST_ONCE);    // 적어도 한번 처리
        configs.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE_V2);  // 단 한번만 메시지 처리

        return new KafkaStreamsConfiguration(configs);
    }

}
