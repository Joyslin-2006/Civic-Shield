package com.example.worker_service.kafka;

import lombok.Data;

@Data
public class IssueCreatedEvent {
    private Long issueId;

    private String issueType;

    private String pincode;
}
