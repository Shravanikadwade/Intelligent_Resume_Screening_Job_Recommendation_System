package com.aiResumeApplication.ai_resume_system.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(Long id) {
        super("Job not found with ID: " + id);
    }
}