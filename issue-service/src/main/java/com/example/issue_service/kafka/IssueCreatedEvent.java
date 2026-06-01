package com.example.issue_service.kafka;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IssueCreatedEvent {
    private Long issueId;
    private String issueType;
    private String pincode;
}
