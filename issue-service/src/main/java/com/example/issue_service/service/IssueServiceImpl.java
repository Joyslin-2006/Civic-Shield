package com.example.issue_service.service;

import com.example.issue_service.dto.IssueRequest;
import com.example.issue_service.entity.Issue;
import com.example.issue_service.kafka.IssueCreatedEvent;
import com.example.issue_service.kafka.KafkaProducerService;
import com.example.issue_service.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor

public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final KafkaProducerService kafkaProducerService;


    @Override
    public Issue createIssue(IssueRequest request) {
        if(issueRepository.existsByIssueTypeAndPincodeAndStatus(
                request.getIssueType(),
                request.getPincode(),
                "OPEN")) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Duplicate issue already exists"
            );
        }
        String priority;

        switch (request.getUrgency().toUpperCase()) {
            case "HIGH" -> priority = "P1";
            case "MEDIUM" -> priority = "P2";
            default -> priority = "P3";
        }

        String department;

        switch (request.getIssueType().toUpperCase()) {
            case "STREETLIGHT" -> department = "Electrical";
            case "ROAD" -> department = "Road Maintenance";
            case "DRAINAGE" -> department = "Water Department";
            default -> department = "General";
        }

        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .issueType(request.getIssueType())
                .location(request.getLocation())
                .pincode(request.getPincode())
                .urgency(request.getUrgency())
                .priority(priority)
                .status("OPEN")
                .assignedDepartment(department)
                .build();

        Issue savedIssue = issueRepository.save(issue);

// Publish Kafka event
        kafkaProducerService.publishIssueCreated(
                new IssueCreatedEvent(
                        savedIssue.getId(),
                        savedIssue.getIssueType(),
                        savedIssue.getPincode()
                )
        );

        return savedIssue;
    }

    @Override
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
}
