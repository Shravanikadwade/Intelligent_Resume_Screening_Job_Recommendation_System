package com.aiResumeApplication.ai_resume_system.controller;

import com.aiResumeApplication.ai_resume_system.dto.CandidateRanking;
import com.aiResumeApplication.ai_resume_system.model.Job;
import com.aiResumeApplication.ai_resume_system.service.CandidateRankingService;
import com.aiResumeApplication.ai_resume_system.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private CandidateRankingService candidateRankingService;

    @Autowired
    private JobService jobService;


    @GetMapping("/{jobId}/rank-candidates")
    public ResponseEntity<List<CandidateRanking>> rankCandidates(
            @PathVariable Long jobId) {


        System.out.println("=== Rank Candidates API Hit ===");



        List<CandidateRanking> rankings =
                candidateRankingService.rankCandidates(jobId);

        return ResponseEntity.ok(rankings);
    }

    @PostMapping("/add")
    public Job addJob(@Valid @RequestBody Job job) {
        return jobService.addJob(job);
    }
    @GetMapping("/all")
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();
    }
}
