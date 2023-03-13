package com.example.profitsoft_kafka_project.messaging;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class KafkaMessage {
    private String subject;
    private String text;
    private String email;
}
