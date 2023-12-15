package ru.clevertec.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumer {

    @RabbitListener(queues = {"queue.quorum"})
    public void consumeQuorumQueueMessage(String message) {
        log.info("Received message from quorum.queue1: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.quorum"})
    public void consumeQuorumQueueTwoMessage(String message) {
        log.info("Received message from quorum.queue2: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.direct1"})
    public void consumeDirectOneQueueMessage(String message) {
        log.info("Received message from queue.direct1: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.direct2"})
    public void consumeDirectTwoQueueMessage(String message) {
        log.info("Received message from queue.direct2: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.fanout1"})
    public void consumeFanoutOneQueueMessage(String message) {
        log.info("Received message from queue.fanout1: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.fanout2"})
    public void consumeFanoutTwoQueueMessage(String message) {
        log.info("Received message from queue.fanout2: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.topic1"})
    public void consumeTopicOneQueueMessage(Message message) {
        log.info("Received message from queue.topic1: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.topic2"})
    public void consumeTopicTwoQueueMessage(Message message) {
        log.info("Received message from queue.topic2: {}", message);
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.header1"})
    public void consumeHeaderOneQueueMessage(Message message, AcknowledgeMode mode) {
        log.info("Received message from queue.header1: {} and headers: {}", new String(message.getBody()), message.getMessageProperties().getHeaders());
        log.info("--------------------------------------------------");
    }

    @RabbitListener(queues = {"queue.header2"})
    public void consumeHeaderTwoQueueMessage(Message message) {
        log.info("Received message from queue.header2: {} and headers: {}", new String(message.getBody()), message.getMessageProperties().getHeaders());
        log.info("--------------------------------------------------");
    }
    @RabbitListener(queues = {"queue.header3"})
    public void consumeHeaderThreeQueueMessage(Message message) {
        log.info("Received message from queue.header3: {} and headers: {}", new String(message.getBody()), message.getMessageProperties().getHeaders());
        log.info("--------------------------------------------------");
    }
}
