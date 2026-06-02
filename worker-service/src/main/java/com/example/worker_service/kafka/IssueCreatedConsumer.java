package com.example.worker_service.kafka;
import com.example.worker_service.entity.Assignment;
import com.example.worker_service.entity.Worker;
import com.example.worker_service.repository.AssignmentRepository;
import com.example.worker_service.repository.WorkerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueCreatedConsumer {
    private final ObjectMapper objectMapper;

    private final WorkerRepository workerRepository;

    private final AssignmentRepository assignmentRepository;

    @KafkaListener(
            topics = "issue-created-topic",
            groupId = "worker-group"
    )
    public void consume(String message) throws Exception {

        IssueCreatedEvent event =
                objectMapper.readValue(
                        message,
                        IssueCreatedEvent.class
                );

        String department;

        switch (event.getIssueType().toUpperCase()) {
            case "ROAD" -> department = "Road Maintenance";
            case "DRAINAGE" -> department = "Water Department";
            case "STREETLIGHT" -> department = "Electrical";
            default -> department = "General";
        }

        Optional<Worker> workerOptional =
                workerRepository
                        .findFirstByDepartmentAndPincodeAndAvailableTrue(
                                department,
                                event.getPincode()
                        );

        if (workerOptional.isEmpty()) {

            System.out.println(
                    "No worker found for issue "
                            + event.getIssueId()
            );

            return;
        }

        Worker worker = workerOptional.get();

        Assignment assignment =
                Assignment.builder()
                        .issueId(event.getIssueId())
                        .workerId(worker.getId())
                        .status("ASSIGNED")
                        .build();

        assignmentRepository.save(assignment);

        worker.setAvailable(false);

        workerRepository.save(worker);

        System.out.println(
                "Issue "
                        + event.getIssueId()
                        + " assigned to "
                        + worker.getName()
        );
    }
}
