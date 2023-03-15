package com.example.profitsoft_kafka_project.repository;

import com.example.profitsoft_kafka_project.entity.Message;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

@Repository
public class CustomRepositoryImpl implements CustomRepository{
    private final ElasticsearchOperations operations;
    public CustomRepositoryImpl(ElasticsearchOperations operations) {
        this.operations = operations;
    }
    @Override
    public void save (Message message) {
         operations.save(message);
    }
}
