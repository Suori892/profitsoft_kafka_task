package com.example.profitsoft_kafka_project.listener;

import com.example.profitsoft_kafka_project.messaging.KafkaMessage;
import com.example.profitsoft_kafka_project.service.interfaces.MessageService;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class MessageListener {
    private final MessageService messageService;
    public MessageListener(MessageService messageService) {
        this.messageService = messageService;
    }
    @KafkaListener(topics = "kafka.topic", groupId = "com.example")
    public void messageReceived(KafkaMessage message) {
        messageService.save(message);
    }
}
