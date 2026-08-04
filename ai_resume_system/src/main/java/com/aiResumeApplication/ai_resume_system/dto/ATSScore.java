package com.aiResumeApplication.ai_resume_system.dto;

public class ATSScore {

    private double atsScore;
    private double skillScore;
    private double resumeCompleteness;
    private double skillCountScore;

    public ATSScore() {
    }

    public ATSScore(double atsScore,
                    double skillScore,
                    double resumeCompleteness,
                    double skillCountScore) {

        this.atsScore = atsScore;
        this.skillScore = skillScore;
        this.resumeCompleteness = resumeCompleteness;
        this.skillCountScore = skillCountScore;
    }

    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }

    public double getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(double skillScore) {
        this.skillScore = skillScore;
    }

    public double getResumeCompleteness() {
        return resumeCompleteness;
    }

    public void setResumeCompleteness(double resumeCompleteness) {
        this.resumeCompleteness = resumeCompleteness;
    }

    public double getSkillCountScore() {
        return skillCountScore;
    }

    public void setSkillCountScore(double skillCountScore) {
        this.skillCountScore = skillCountScore;
    }
}