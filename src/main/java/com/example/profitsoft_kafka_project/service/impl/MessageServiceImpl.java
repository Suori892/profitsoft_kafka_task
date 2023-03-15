package com.example.profitsoft_kafka_project.service.impl;

import com.example.profitsoft_kafka_project.entity.Message;
import com.example.profitsoft_kafka_project.entity.Status;
import com.example.profitsoft_kafka_project.messaging.KafkaMessage;
import com.example.profitsoft_kafka_project.repository.MessageRepository;
import com.example.profitsoft_kafka_project.service.EmailService;
import com.example.profitsoft_kafka_project.service.interfaces.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class MessageServiceImpl implements MessageService {
    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final EmailService emailService;
    private final MessageRepository repository;

    public MessageServiceImpl(EmailService emailService, MessageRepository repository) {
        this.emailService = emailService;
        this.repository = repository;
    }

    @Override
    public void save(KafkaMessage message) {
        Message emailMessage = convertKafkaMessageToMessage(message);
        mailSending(emailMessage);
        repository.save(emailMessage);
    }
    @Scheduled(fixedRate = 300000)
    private void messageResending() {
        getAllFailedMessages()
                .forEach(this::mailSending);
    }
    private void mailSending(Message message) {
        try {
            emailService.sendEmail(message.getEmail(), message.getSubject(), message.getText());
            message.setMessageStatus(Status.SENT);
        } catch (MailException mailException) {
            logger.error("Error while sending out email..{}", mailException.getStackTrace());
            message.setMessageStatus(Status.FAILED);
        }
    }
    private List<Message> getAllFailedMessages() {
        return repository.findAllByMessageStatus(Status.FAILED);
    }
    private Message convertKafkaMessageToMessage(KafkaMessage message) {
        return Message.builder()
                .subject(message.getSubject())
                .text(message.getText())
                .email(message.getEmail())
                .build();
    }
}
