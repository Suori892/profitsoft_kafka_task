package com.example.profitsoft_kafka_project.repository;

import com.example.profitsoft_kafka_project.entity.Message;
import com.example.profitsoft_kafka_project.entity.Status;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends ElasticsearchRepository<Message, String>, CustomRepository {
    List<Message> findAllByMessageStatus(Status status);
}
