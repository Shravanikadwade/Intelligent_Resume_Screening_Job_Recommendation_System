package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.model.Job;
import com.aiResumeApplication.ai_resume_system.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JobService {

    private static final Logger logger =
            LoggerFactory.getLogger(JobService.class);

    @Autowired
    private JobRepository jobRepository;

    public Job addJob(Job job) {

        logger.info("Adding new job: {}", job.getTitle());

        Job savedJob = jobRepository.save(job);

        logger.info("Job added successfully with ID: {}", savedJob.getId());

        return savedJob;
    }

    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }
}
