package com.example.profitsoft_kafka_project.service.interfaces;

import com.example.profitsoft_kafka_project.dto.DetailedMessageDto;
import com.example.profitsoft_kafka_project.dto.MessageDto;
import com.example.profitsoft_kafka_project.messaging.KafkaMessage;

import java.util.List;

public interface MessageService {
    String save(KafkaMessage message);
    List<DetailedMessageDto> getAllFailedMessages();
}
