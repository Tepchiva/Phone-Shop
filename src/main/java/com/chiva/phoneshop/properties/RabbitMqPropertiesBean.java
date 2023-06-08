package com.chiva.phoneshop.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqPropertiesBean {

    @Bean("rabbitmq.notification.multi-message")
    @ConfigurationProperties(prefix = "rabbitmq.notification.multi-message")
    public RabbitMqProperties rabbitMqNotificationMultiMessageQueue() {
        return new RabbitMqProperties();
    }

    @Bean("rabbitmq.notification.multi-message.error")
    @ConfigurationProperties(prefix = "rabbitmq.notification.multi-message.error")
    public RabbitMqProperties rabbitMqNotificationMultiMessageErrorQueue() {
        return new RabbitMqProperties();
    }
}