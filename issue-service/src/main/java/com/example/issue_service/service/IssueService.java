package com.example.issue_service.service;

import com.example.issue_service.dto.IssueRequest;
import com.example.issue_service.entity.Issue;

import java.util.List;

public interface IssueService {

    Issue createIssue(IssueRequest request);

    List<Issue> getAllIssues();
}
