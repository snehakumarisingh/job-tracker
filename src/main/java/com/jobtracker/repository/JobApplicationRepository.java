package com.jobtracker.repository;

import com.jobtracker.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {
                
        List<JobApplication> findByCompanyNameContainingIgnoreCase(String companyName);
        List<JobApplication> findByStatusIgnoreCase(String status);
}