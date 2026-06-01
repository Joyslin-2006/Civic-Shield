package com.example.issue_service.repository;

import com.example.issue_service.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    boolean existsByIssueTypeAndPincodeAndStatus(
            String issueType,
            String pincode,
            String status
    );

}
