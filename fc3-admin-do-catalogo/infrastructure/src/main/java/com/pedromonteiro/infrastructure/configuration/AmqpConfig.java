package com.pedromonteiro.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedromonteiro.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.pedromonteiro.infrastructure.configuration.annotations.VideoEncodedQueue;
import com.pedromonteiro.infrastructure.configuration.properties.amqp.QueueProperties;

@Configuration
public class AmqpConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.video-created")
    @VideoCreatedQueue
    QueueProperties videoCreatedQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    @ConfigurationProperties("amqp.queues.video-encoded")
    @VideoEncodedQueue
    QueueProperties videoEncodedQueueProperties() {
        return new QueueProperties();
    }

    @Bean
    public String queueName(@VideoCreatedQueue QueueProperties props) {
        return props.getQueue();
    }
}
