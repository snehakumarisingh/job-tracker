package com.jobtracker.service;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public JobApplication addJob(JobApplication job) {
        if (job.getStatus() == null || job.getStatus().isBlank()) {
            job.setStatus("Applied");
        }

        if (job.getApplicationDate() == null) {
            job.setApplicationDate(java.time.LocalDate.now());
        }

        return repository.save(job);
    }

    
    public java.util.List<JobApplication> getAllJobs() {
    return repository.findAll();
}


public JobApplication updateStatus(Long id, String status) {
    JobApplication job = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Job application not found"));

    job.setStatus(status);
    return repository.save(job);
}

public void deleteJob(Long id) {
    repository.deleteById(id);
}

}