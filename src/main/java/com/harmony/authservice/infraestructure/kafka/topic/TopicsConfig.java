package com.harmony.authservice.infraestructure.kafka.topic;


import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class TopicsConfig {

    @Bean
    public NewTopic topicPasswordRecoveryToken() {
        return TopicBuilder.name("password-recovery-token")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
