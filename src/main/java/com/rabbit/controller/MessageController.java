package com.rabbit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.publisher.RabbitMqProducer;

@RestController
public class MessageController {

    private final RabbitMqProducer mqProducer;

    public MessageController(RabbitMqProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

    @GetMapping("/publisher")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        mqProducer.sendMessage(message);
        return ResponseEntity.ok("Message Sent To MQ....");
    }
}
