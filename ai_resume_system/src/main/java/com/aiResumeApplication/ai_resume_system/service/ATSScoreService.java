package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.dto.ATSScore;
import com.aiResumeApplication.ai_resume_system.dto.MatchResult;
import com.aiResumeApplication.ai_resume_system.exception.JobNotFoundException;
import com.aiResumeApplication.ai_resume_system.exception.ResumeNotFoundException;
import com.aiResumeApplication.ai_resume_system.model.Job;
import com.aiResumeApplication.ai_resume_system.model.Resume;
import com.aiResumeApplication.ai_resume_system.repository.JobRepository;
import com.aiResumeApplication.ai_resume_system.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ATSScoreService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeMatchingService resumeMatchingService;

    public ATSScore calculateATSScore(Long resumeId, Long jobId) {

        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);

        if (resumeOptional.isEmpty()) {
            throw new ResumeNotFoundException(resumeId);
        }

        if (jobOptional.isEmpty()) {
            throw new JobNotFoundException(jobId);
        }

        Resume resume = resumeOptional.get();
        Job job = jobOptional.get();

        MatchResult matchResult = resumeMatchingService.calculateMatch(
                resume.getSkills(),
                job.getRequiredSkills()
        );

        double skillScore = matchResult.getMatchScore();

        // Resume Completeness
        double resumeCompleteness = 100;

        if (resume.getCandidateName() == null || resume.getCandidateName().isBlank()) {
            resumeCompleteness -= 20;
        }

        if (resume.getSkills() == null || resume.getSkills().isBlank()) {
            resumeCompleteness -= 80;
        }

        // Skill Count Score
        int skillCount = resume.getSkills().split(",").length;

        double skillCountScore;

        if (skillCount >= 15) {
            skillCountScore = 100;
        } else {
            skillCountScore = (skillCount / 15.0) * 100;
        }

        // Final ATS Score
        double atsScore =
                (skillScore * 0.60)
                        + (resumeCompleteness * 0.20)
                        + (skillCountScore * 0.20);

        return new ATSScore(
                atsScore,
                skillScore,
                resumeCompleteness,
                skillCountScore
        );
    }
}