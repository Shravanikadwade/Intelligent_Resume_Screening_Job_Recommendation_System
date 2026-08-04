package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.model.Resume;
import com.aiResumeApplication.ai_resume_system.repository.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    private static final Logger logger =
            LoggerFactory.getLogger(ResumeService.class);

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume saveResume(Resume resume) {

        logger.info("Saving resume for candidate: {}", resume.getCandidateName());

        Resume savedResume = resumeRepository.save(resume);

        logger.info("Resume saved successfully with ID: {}", savedResume.getId());

        return savedResume;
    }
}
