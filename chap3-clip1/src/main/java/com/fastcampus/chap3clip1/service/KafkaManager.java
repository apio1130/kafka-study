package com.fastcampus.chap3clip1.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AlterConfigOp;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaManager {

    private final KafkaAdmin kafkaAdmin;

    private final AdminClient adminClient;

    public KafkaManager(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    public void describeTopicConfigs() throws ExecutionException, InterruptedException {
        Collection<ConfigResource> resource = List.of(
                new ConfigResource(ConfigResource.Type.TOPIC, "clip4-listener")
        );

        DescribeConfigsResult result = adminClient.describeConfigs(resource);
        System.out.println(result.all().get());
    }

    public void changeConfig() {
        // retention 시간 변경
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, "clip4-listener");
        Map<ConfigResource, Collection<AlterConfigOp>> ops = new HashMap<>();
//        ops.put(resource, List.of(new AlterConfigOp(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, "6000"), AlterConfigOp.OpType.SET)));  // 변경
        ops.put(resource, List.of(new AlterConfigOp(new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, null), AlterConfigOp.OpType.DELETE))); // 초기화

        adminClient.incrementalAlterConfigs(ops);
    }


}
