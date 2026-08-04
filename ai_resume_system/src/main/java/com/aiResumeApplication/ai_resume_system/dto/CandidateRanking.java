package com.aiResumeApplication.ai_resume_system.dto;

public class CandidateRanking {

    private Long resumeId;
    private String candidateName;
    private double matchScore;

    public CandidateRanking() {
    }

    public CandidateRanking(Long resumeId, String candidateName, double matchScore) {
        this.resumeId = resumeId;
        this.candidateName = candidateName;
        this.matchScore = matchScore;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }
}