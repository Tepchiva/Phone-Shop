package com.chiva.phoneshop.produce;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.notification.exchange}")
    private String psExchange;

    @Value("${rabbitmq.notification.multi-message.routing-key}")
    private String psMultiMessageRoutingKey;

    public void push(Object object) {
        rabbitTemplate.convertAndSend(psExchange, psMultiMessageRoutingKey, object.toString());
    }
}
