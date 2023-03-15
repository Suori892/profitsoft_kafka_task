package com.example.profitsoft_kafka_project.service.interfaces;

import com.example.profitsoft_kafka_project.messaging.KafkaMessage;
public interface MessageService {
    void save(KafkaMessage message);
}
