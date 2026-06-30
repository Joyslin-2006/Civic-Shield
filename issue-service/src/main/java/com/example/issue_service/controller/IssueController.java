package com.example.issue_service.controller;

import com.example.issue_service.dto.IssueRequest;
import com.example.issue_service.entity.Issue;
import com.example.issue_service.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class IssueController {
    private final IssueService issueService;

    @PostMapping
    public Issue createIssue(@RequestBody IssueRequest request) {
        return issueService.createIssue(request);
    }

    @GetMapping
    public List<Issue> getAllIssues() {
        return issueService.getAllIssues();
    }
}
