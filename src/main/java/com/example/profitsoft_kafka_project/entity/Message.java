package com.example.profitsoft_kafka_project.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "email_message")
public class Message {
    @Id
    private String id;
    private String subject;
    private String text;
    private String email;
    private Status messageStatus;
}
