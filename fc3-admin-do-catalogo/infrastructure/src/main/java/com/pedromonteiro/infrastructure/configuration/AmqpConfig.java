package com.pedromonteiro.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pedromonteiro.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.pedromonteiro.infrastructure.configuration.annotations.VideoEncodedQueue;
import com.pedromonteiro.infrastructure.configuration.annotations.VideoEvents;
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

    @Configuration
    static class Admin {

        @Bean
        @VideoEvents
        Exchange videoEventsExchange(@VideoCreatedQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @VideoCreatedQueue
        Queue videoCreatedQueue(@VideoCreatedQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @VideoCreatedQueue
        Binding videoCreatedQueueBinding(
                @VideoEvents DirectExchange exchange,
                @VideoCreatedQueue Queue queue,
                @VideoCreatedQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }

        @Bean
        @VideoEncodedQueue
        Queue videoEncodedQueue(@VideoEncodedQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @VideoEncodedQueue
        Binding videoEncodedQueueBinding(
                @VideoEvents DirectExchange exchange,
                @VideoEncodedQueue Queue queue,
                @VideoEncodedQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }
    }
}
