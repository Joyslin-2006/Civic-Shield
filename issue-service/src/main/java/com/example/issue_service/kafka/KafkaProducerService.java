package com.example.issue_service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishIssueCreated(IssueCreatedEvent event) {

        kafkaTemplate.send(
                "issue-created-topic",
                event
        );
    }

}
