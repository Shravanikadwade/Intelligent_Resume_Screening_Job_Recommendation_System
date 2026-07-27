package com.aiResumeApplication.ai_resume_system.controller;

import com.aiResumeApplication.ai_resume_system.dto.ATSScore;
import com.aiResumeApplication.ai_resume_system.dto.JobRecommendation;
import com.aiResumeApplication.ai_resume_system.dto.MatchResult;
import com.aiResumeApplication.ai_resume_system.dto.ResumeFeedback;
import com.aiResumeApplication.ai_resume_system.model.Job;
import com.aiResumeApplication.ai_resume_system.model.Resume;
import com.aiResumeApplication.ai_resume_system.repository.JobRepository;
import com.aiResumeApplication.ai_resume_system.repository.ResumeRepository;
import com.aiResumeApplication.ai_resume_system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeFeedbackService resumeFeedbackService;

    @Autowired
    private ATSScoreService atsScoreService;

    @Autowired
    private JobRecommendationService jobRecommendationService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ResumeMatchingService resumeMatchingService;

    @Autowired
    private SkillExtractionService skillExtractionService;

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/feedback/{resumeId}/{jobId}")
    public ResponseEntity<ResumeFeedback> getResumeFeedback(
            @PathVariable Long resumeId,
            @PathVariable Long jobId) {

        ResumeFeedback feedback =
                resumeFeedbackService.generateFeedback(resumeId, jobId);

        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/ats-score/{resumeId}/{jobId}")
    public ResponseEntity<ATSScore> calculateATSScore(
            @PathVariable Long resumeId,
            @PathVariable Long jobId) {

        ATSScore atsScore =
                atsScoreService.calculateATSScore(resumeId, jobId);

        return ResponseEntity.ok(atsScore);
    }

    @GetMapping("/recommend/{resumeId}")
    public ResponseEntity<List<JobRecommendation>> recommendJobs(
            @PathVariable Long resumeId) {

        List<JobRecommendation> recommendations =
                jobRecommendationService.recommendJobs(resumeId);

        return ResponseEntity.ok(recommendations);
    }


    @GetMapping("/match/{resumeId}/{jobId}")
    public ResponseEntity<?> matchResumeWithJob(
            @PathVariable Long resumeId,
            @PathVariable Long jobId) {

        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);
        Optional<Job> jobOptional = jobRepository.findById(jobId);

        if (resumeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Resume not found.");
        }

        if (jobOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Job not found.");
        }

        Resume resume = resumeOptional.get();
        Job job = jobOptional.get();

        MatchResult result = resumeMatchingService.calculateMatch(
                resume.getSkills(),
                job.getRequiredSkills()
        );

        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("candidateName") String candidateName
    ) {

        try {

            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            File directory = new File(uploadDir);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            // Step 1: Save PDF
            file.transferTo(new File(filePath));
            // Step 2: Open PDF
            PDDocument document = PDDocument.load(new File(filePath));
            // Step 3: Create PDFTextStripper
            PDFTextStripper stripper = new PDFTextStripper();
            //Step 4: Extract Text  ← ADD THIS HERE
            String extractedText = stripper.getText(document);
            // Step 5: Close PDF
            document.close();

            // Step 6: Extract Skills
            String skills = skillExtractionService.extractSkills(extractedText);
            // Step 7: Save into Database
            Resume resume = new Resume();

            resume.setCandidateName(candidateName);
            resume.setFileName(file.getOriginalFilename());
            resume.setFilePath(filePath);
            resume.setExtractedText(extractedText);
            resume.setSkills(skills);

            resumeService.saveResume(resume);

            return ResponseEntity.ok("Resume uploaded successfully");

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("File upload failed");
        }
    }
}

