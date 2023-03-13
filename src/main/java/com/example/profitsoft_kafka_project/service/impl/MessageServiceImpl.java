package com.example.profitsoft_kafka_project.service.impl;

import com.example.profitsoft_kafka_project.dto.DetailedMessageDto;
import com.example.profitsoft_kafka_project.entity.Message;
import com.example.profitsoft_kafka_project.entity.Status;
import com.example.profitsoft_kafka_project.messaging.KafkaMessage;
import com.example.profitsoft_kafka_project.repository.MessageRepository;
import com.example.profitsoft_kafka_project.service.interfaces.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;

    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String save(KafkaMessage message) {
        Message emailMessage = new Message();
        emailMessage.setSubject(message.getSubject());
        emailMessage.setText(message.getText());
        emailMessage.setEmail(message.getEmail());
        emailMessage.setMessageStatus(Status.SENT);

        return repository.save(emailMessage);
    }

    @Override
    public List<DetailedMessageDto> getAllFailedMessages() {
        return repository.findAllByMessageStatus(Status.FAILED)
                .stream()
                .map(this::convertMessageToDto)
                .collect(Collectors.toList());
    }

    private DetailedMessageDto convertMessageToDto(Message message) {
        return DetailedMessageDto.builder()
                .id(message.getId())
                .subject(message.getSubject())
                .text(message.getText())
                .email(message.getEmail())
                .messageStatus(message.getMessageStatus())
                .build();
    }
}
