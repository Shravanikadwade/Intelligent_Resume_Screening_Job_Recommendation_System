package com.aiResumeApplication.ai_resume_system.exception;


public class ResumeNotFoundException extends RuntimeException {

    public ResumeNotFoundException(Long id) {
        super("Resume not found with ID: " + id);
    }
}