package com.chiva.phoneshop.properties;

import lombok.Data;

@Data
public class RabbitMqProperties {
    private String queue;
    private String routingKey;
}
