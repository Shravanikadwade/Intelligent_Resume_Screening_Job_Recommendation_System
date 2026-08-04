package com.aiResumeApplication.ai_resume_system.service;


import com.aiResumeApplication.ai_resume_system.dto.ATSScore;
import com.aiResumeApplication.ai_resume_system.dto.MatchResult;
import com.aiResumeApplication.ai_resume_system.dto.ResumeFeedback;
import com.aiResumeApplication.ai_resume_system.exception.JobNotFoundException;
import com.aiResumeApplication.ai_resume_system.exception.ResumeNotFoundException;
import com.aiResumeApplication.ai_resume_system.model.Job;
import com.aiResumeApplication.ai_resume_system.model.Resume;
import com.aiResumeApplication.ai_resume_system.repository.JobRepository;
import com.aiResumeApplication.ai_resume_system.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeFeedbackService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeMatchingService resumeMatchingService;

    @Autowired
    private ATSScoreService atsScoreService;

    public ResumeFeedback generateFeedback(Long resumeId, Long jobId) {

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

        ATSScore atsScore = atsScoreService.calculateATSScore(resumeId, jobId);

        List<String> strengths = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();

        // Strengths
        for (String skill : matchResult.getMatchedSkills()) {
            strengths.add("Strong knowledge of " + skill);
        }

        // Suggestions
        for (String skill : matchResult.getMissingSkills()) {
            suggestions.add("Learn " + skill + " to improve your ATS score.");
        }

        if (atsScore.getAtsScore() >= 90) {
            suggestions.add("Excellent resume. Keep your skills updated.");
        } else if (atsScore.getAtsScore() >= 70) {
            suggestions.add("Add more projects related to your skills.");
        } else {
            suggestions.add("Improve your resume by learning the missing technologies.");
        }

        return new ResumeFeedback(
                atsScore.getAtsScore(),
                strengths,
                matchResult.getMissingSkills(),
                suggestions
        );
    }
}