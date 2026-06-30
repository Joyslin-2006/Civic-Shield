package com.example.worker_service.controller;

import com.example.worker_service.entity.Worker;
import com.example.worker_service.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class WorkerController {
    private final WorkerRepository workerRepository;

    @PostMapping
    public Worker createWorker(
            @RequestBody Worker worker
    ) {
        return workerRepository.save(worker);
    }

    @GetMapping
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }
}
