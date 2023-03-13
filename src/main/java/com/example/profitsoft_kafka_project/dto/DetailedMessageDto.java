package com.example.profitsoft_kafka_project.dto;

import com.example.profitsoft_kafka_project.entity.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DetailedMessageDto {
    private String id;
    private String subject;
    private String text;
    private String email;
    private Status messageStatus;
}
