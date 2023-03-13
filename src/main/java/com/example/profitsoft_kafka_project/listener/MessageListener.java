package com.example.profitsoft_kafka_project.listener;

import com.example.profitsoft_kafka_project.messaging.KafkaMessage;
import com.example.profitsoft_kafka_project.service.EmailService;
import com.example.profitsoft_kafka_project.service.interfaces.MessageService;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class MessageListener {
    private final MessageService messageService;
    private final EmailService emailService;

    public MessageListener(MessageService messageService, EmailService emailService) {
        this.messageService = messageService;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "kafka.topic", groupId = "com.example")
    public void messageReceived(KafkaMessage message) {
        String id = messageService.save(message);
        emailService.sendEmail(message.getEmail(), message.getSubject(), message.getText());
    }

}
