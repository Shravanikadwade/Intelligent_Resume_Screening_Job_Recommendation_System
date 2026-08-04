package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.dto.CandidateRanking;
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
public class CandidateRankingService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeMatchingService resumeMatchingService;

    public List<CandidateRanking> rankCandidates(Long jobId) {

        Optional<Job> jobOptional = jobRepository.findById(jobId);

        if (jobOptional.isEmpty()) {
            throw new RuntimeException("Job not found");
        }

        Job job = jobOptional.get();

        List<Resume> resumes = resumeRepository.findAll();

        List<CandidateRanking> rankings = new ArrayList<>();

        for (Resume resume : resumes) {

            MatchResult result = resumeMatchingService.calculateMatch(
                    resume.getSkills(),
                    job.getRequiredSkills()
            );

            CandidateRanking ranking = new CandidateRanking(
                    resume.getId(),
                    resume.getCandidateName(),
                    result.getMatchScore()
            );

            rankings.add(ranking);
        }

        rankings.sort(
                Comparator.comparingDouble(CandidateRanking::getMatchScore)
                        .reversed()
        );

        return rankings;
    }
}