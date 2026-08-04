package com.aiResumeApplication.ai_resume_system.dto;


import java.util.List;

public class ResumeFeedback {

    private double atsScore;
    private List<String> strengths;
    private List<String> missingSkills;
    private List<String> suggestions;

    public ResumeFeedback() {
    }

    public ResumeFeedback(double atsScore,
                          List<String> strengths,
                          List<String> missingSkills,
                          List<String> suggestions) {
        this.atsScore = atsScore;
        this.strengths = strengths;
        this.missingSkills = missingSkills;
        this.suggestions = suggestions;
    }

    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}