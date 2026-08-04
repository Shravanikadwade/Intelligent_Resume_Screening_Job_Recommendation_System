package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.dto.JobRecommendation;
import com.aiResumeApplication.ai_resume_system.dto.MatchResult;
import com.aiResumeApplication.ai_resume_system.model.Job;
import com.aiResumeApplication.ai_resume_system.model.Resume;
import com.aiResumeApplication.ai_resume_system.repository.JobRepository;
import com.aiResumeApplication.ai_resume_system.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class JobRecommendationService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeMatchingService resumeMatchingService;

    public List<JobRecommendation> recommendJobs(Long resumeId) {

        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);

        if (resumeOptional.isEmpty()) {
            throw new RuntimeException("Resume not found");
        }

        Resume resume = resumeOptional.get();

        List<Job> jobs = jobRepository.findAll();

        List<JobRecommendation> recommendations = new ArrayList<>();

        for (Job job : jobs) {

            MatchResult result = resumeMatchingService.calculateMatch(
                    resume.getSkills(),
                    job.getRequiredSkills()
            );

            JobRecommendation recommendation = new JobRecommendation(
                    job.getId(),
                    job.getTitle(),
                    result.getMatchScore()
            );

            recommendations.add(recommendation);
        }

        recommendations.sort(
                Comparator.comparingDouble(JobRecommendation::getMatchScore)
                        .reversed()
        );

        return recommendations;
    }
}