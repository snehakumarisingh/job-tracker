package com.jobtracker.controller;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
public ResponseEntity<JobApplication> addJob(
        @Valid @RequestBody JobApplication job) {
    return ResponseEntity.ok(service.addJob(job));
}

    
@GetMapping
public java.util.List<JobApplication> getAllJobs() {
    return service.getAllJobs();
}


@PutMapping("/{id}/status")
public JobApplication updateStatus(
        @PathVariable Long id,
        @RequestParam String status) {

    return service.updateStatus(id, status);
}
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteJob(@PathVariable Long id) {
    service.deleteJob(id);
    return ResponseEntity.ok("Job application deleted successfully");
}
}