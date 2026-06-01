package com.example.issue_service.dto;

import lombok.Data;

@Data
public class IssueRequest {
    private String title;
    private String description;
    private String issueType;
    private String location;
    private String pincode;
    private String urgency;
}
