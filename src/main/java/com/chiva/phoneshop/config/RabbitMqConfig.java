package com.chiva.phoneshop.config;

import com.chiva.phoneshop.properties.RabbitMqProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.notification.exchange}")
    private String psExchange;

    @Bean
    public Declarables topicBinding(
            @Qualifier("rabbitmq.notification.multi-message") RabbitMqProperties psMultiMessageProps,
            @Qualifier("rabbitmq.notification.multi-message.error") RabbitMqProperties psMultiMessageErrorProps
    ) {
        // Exchange
        TopicExchange psTopicExchange = new TopicExchange(psExchange);

        // Queue
        Queue psMultiMessageQueue = new Queue(psMultiMessageProps.getQueue());
        Queue psMultiMessageErrorQueue = new Queue(psMultiMessageErrorProps.getQueue());

        // Binding
        return new Declarables(
                psMultiMessageQueue,
                psMultiMessageErrorQueue,
                psTopicExchange,
                BindingBuilder.bind(psMultiMessageQueue).to(psTopicExchange).with(psMultiMessageProps.getRoutingKey()),
                BindingBuilder.bind(psMultiMessageErrorQueue).to(psTopicExchange).with(psMultiMessageErrorProps.getRoutingKey())
        );
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(3);
        factory.setMessageConverter(messageConverter);
        factory.setPrefetchCount(10);

        RetryTemplate retryTemplate = new RetryTemplate();
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();

        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        factory.setRetryTemplate(retryTemplate);
        return factory;
    }
}