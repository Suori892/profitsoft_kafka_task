package com.example.profitsoft_kafka_project.repository;

import com.example.profitsoft_kafka_project.entity.Message;

public interface CustomRepository {
    void save(Message message);
}
