package com.example.profitsoft_kafka_project.controller;

import com.example.profitsoft_kafka_project.dto.MessageDto;
import com.example.profitsoft_kafka_project.messaging.KafkaMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email-service")
public class MessageController {
    private final KafkaTemplate<String, KafkaMessage> operations;
    @Value("${kafka.topic}")
    private String sendEmailTopic;
    public MessageController(KafkaTemplate<String, KafkaMessage> operations) {
        this.operations = operations;
    }
    @PostMapping
    public void sendEmail(@RequestBody MessageDto messageDto) {
        operations.send(sendEmailTopic, messageDto.getSubject(), convertToKafkaMessage(messageDto));
    }
    private KafkaMessage convertToKafkaMessage(MessageDto messageDto) {
        return KafkaMessage.builder()
                .subject(messageDto.getSubject())
                .text(messageDto.getText())
                .email(messageDto.getEmail())
                .build();
    }
}
