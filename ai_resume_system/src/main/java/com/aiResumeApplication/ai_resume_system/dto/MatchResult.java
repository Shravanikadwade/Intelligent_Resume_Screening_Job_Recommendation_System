package com.aiResumeApplication.ai_resume_system.dto;

import java.util.List;

public class MatchResult {

    private double matchScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;

    public MatchResult() {
    }

    public MatchResult(double matchScore, List<String> matchedSkills, List<String> missingSkills) {
        this.matchScore = matchScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
