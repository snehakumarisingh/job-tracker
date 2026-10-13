package com.jobtracker.service;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import com.jobtracker.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

import java.util.Map;
import java.util.HashMap;

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
            job.setApplicationDate(LocalDate.now());
        }

        return repository.save(job);
    }

    public List<JobApplication> getAllJobs() {
        return repository.findAll();
    }

   public JobApplication updateStatus(Long id, String status) {

    if (status == null ||
        !status.matches("Applied|Interview|Selected|Rejected")) {
        throw new IllegalArgumentException(
            "Status must be Applied, Interview, Selected, or Rejected"
        );
    }

    JobApplication job = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job application not found with ID: " + id));
    job.setStatus(status);
    return repository.save(job);
}

    public void deleteJob(Long id) {
        JobApplication job = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Job application not found with ID: " + id));
        repository.delete(job);
    }

    public List<JobApplication> searchByCompany(String companyName) {
    return repository.findByCompanyNameContainingIgnoreCase(companyName);
}

public List<JobApplication> filterByStatus(String status) {
    return repository.findByStatusIgnoreCase(status);
}

public Map<String, Long> getApplicationStats() {
    Map<String, Long> stats = new HashMap<>();

    stats.put("totalApplications", repository.count());
    stats.put("applied", repository.countByStatusIgnoreCase("Applied"));
    stats.put("interview", repository.countByStatusIgnoreCase("Interview"));
    stats.put("selected", repository.countByStatusIgnoreCase("Selected"));
    stats.put("rejected", repository.countByStatusIgnoreCase("Rejected"));

    return stats;
}

public JobApplication getJobById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job application not found with ID: " + id));
}
}

