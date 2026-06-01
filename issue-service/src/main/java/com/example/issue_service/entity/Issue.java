package com.example.issue_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String issueType;

    private String location;

    private String pincode;

    private String urgency;

    private String priority;

    private String status;

    private String assignedDepartment;
}
