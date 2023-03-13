package com.example.profitsoft_kafka_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private String subject;
    private String text;
    private String email;
}
