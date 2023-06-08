package com.chiva.phoneshop.controller;

import com.chiva.phoneshop.produce.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
@RequiredArgsConstructor
public class RabbitMqController {

    private final NotificationProducer notificationProducer;

    @GetMapping("push")
    public void push() {
        notificationProducer.push("this is message");
    }
}
