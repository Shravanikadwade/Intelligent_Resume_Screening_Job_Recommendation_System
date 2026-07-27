package com.aiResumeApplication.ai_resume_system.service;

import com.aiResumeApplication.ai_resume_system.dto.MatchResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResumeMatchingService {

    public MatchResult calculateMatch(String resumeSkills, String jobSkills) {

        // Handle null values
        if (resumeSkills == null || resumeSkills.isBlank()) {
            return new MatchResult(0.0, new ArrayList<>(), new ArrayList<>());
        }

        if (jobSkills == null || jobSkills.isBlank()) {
            return new MatchResult(0.0, new ArrayList<>(), new ArrayList<>());
        }

        Set<String> resumeSkillSet = Arrays.stream(resumeSkills.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        List<String> jobSkillList = Arrays.stream(jobSkills.split(","))
                .map(String::trim)
                .toList();

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : jobSkillList) {

            if (resumeSkillSet.contains(skill.toLowerCase())) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        double matchScore =
                ((double) matchedSkills.size() / jobSkillList.size()) * 100;

        return new MatchResult(matchScore, matchedSkills, missingSkills);
    }
}