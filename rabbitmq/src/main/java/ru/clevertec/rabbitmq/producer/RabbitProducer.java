package ru.clevertec.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.clevertec.rabbitmq.controller.RabbitController;
import ru.clevertec.rabbitmq.controller.RabbitController.RouteMessage;
import ru.clevertec.rabbitmq.controller.RabbitController.RouteMessageWithHeaders;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void produceMessage(RouteMessage routeMessage) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);

        Optional.ofNullable(routeMessage)
                .map(this::toRabbitMessage)
                .map(this::toJsonMessage)
                .map(String::getBytes)
                .map(bytes -> new Message(bytes, messageProperties))
                .map(msg -> rabbitTemplate.sendAndReceive(routeMessage.exchange(), routeMessage.routingKey(), msg))
                .ifPresent(msg -> log.info("Received response from rabbit: {}", msg));
    }

    @SneakyThrows
    public void produceMessageWithHeaders(RouteMessageWithHeaders routeMessage) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setHeaders(routeMessage.headers());

        Optional.of(routeMessage)
                .map(this::toRabbitMessage)
                .map(this::toJsonMessage)
                .map(String::getBytes)
                .map(bytes -> new Message(bytes, messageProperties))
                .map(msg -> rabbitTemplate.sendAndReceive(routeMessage.exchange(), "routingKey", msg))
                .ifPresent(msg -> log.info("Received response from rabbit: {}", msg));
    }

    @SneakyThrows
    private <T> String toJsonMessage(T t) {
        return objectMapper.writeValueAsString(t);
    }

    private RabbitMessage toRabbitMessage(RouteMessage routeMessage) {
        return new RabbitMessage(
                UUID.randomUUID().toString(),
                routeMessage.message(),
                LocalDateTime.now()
        );
    }

    private RabbitMessage toRabbitMessage(RouteMessageWithHeaders routeMessage) {
        return new RabbitMessage(
                UUID.randomUUID().toString(),
                routeMessage.message(),
                LocalDateTime.now()
        );
    }

    public record RabbitMessage(String uuid,
                                String payload,
                                LocalDateTime createdAt) {
    }
}
