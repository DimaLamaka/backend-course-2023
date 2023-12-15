package ru.clevertec.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.rabbitmq.producer.RabbitProducer;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class RabbitController {
    private final RabbitProducer rabbitProducer;

    @PostMapping("/publish")
    public ResponseEntity<Void> publishMessage(@RequestBody RouteMessage routeMessage) {
        rabbitProducer.produceMessage(routeMessage);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/publish/header")
    public ResponseEntity<Void> publishMessage(@RequestBody RouteMessageWithHeaders routeMessage) {
        rabbitProducer.produceMessageWithHeaders(routeMessage);
        return ResponseEntity.ok().build();
    }

    public record RouteMessage(String message,
                               String routingKey,
                               String exchange) {
    }

    public record RouteMessageWithHeaders(String message,
                               Map<String, Object> headers,
                               String exchange) {
    }
}
