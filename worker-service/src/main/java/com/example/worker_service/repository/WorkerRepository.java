package com.example.worker_service.repository;

import com.example.worker_service.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long>{
    Optional<Worker> findFirstByDepartmentAndPincodeAndAvailableTrue(
            String department,
            String pincode
    );
}
