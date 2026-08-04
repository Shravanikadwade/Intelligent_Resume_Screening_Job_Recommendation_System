package com.aiResumeApplication.ai_resume_system.dto;

public class JobRecommendation {

    private Long jobId;
    private String jobTitle;
    private double matchScore;

    public JobRecommendation() {
    }

    public JobRecommendation(Long jobId, String jobTitle, double matchScore) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.matchScore = matchScore;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }
}