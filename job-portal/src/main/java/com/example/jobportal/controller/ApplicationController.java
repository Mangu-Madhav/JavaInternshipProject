package com.example.jobportal.controller;

import com.example.jobportal.model.Application;
import com.example.jobportal.model.Job;
import com.example.jobportal.model.User;
import com.example.jobportal.repository.ApplicationRepository;
import com.example.jobportal.repository.JobRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/apply")
public class ApplicationController {
    private final ApplicationRepository appRepo;
    private final JobRepository jobRepo;
    private final UserRepository userRepo;
    public ApplicationController(ApplicationRepository appRepo, JobRepository jobRepo, UserRepository userRepo){
        this.appRepo = appRepo; this.jobRepo = jobRepo; this.userRepo = userRepo;
    }

    @PostMapping("/{jobId}")
    public String apply(@PathVariable Long jobId, @AuthenticationPrincipal UserDetails ud, @RequestParam(required=false) String resumeUrl, Model model){
        if(ud==null) return "redirect:/login";
        Optional<Job> j = jobRepo.findById(jobId);
        if(j.isEmpty()) return "redirect:/";
        User applicant = userRepo.findByEmail(ud.getUsername()).orElse(null);
        Application a = new Application();
        a.setJob(j.get()); a.setApplicant(applicant); a.setResumeUrl(resumeUrl); a.setStatus("APPLIED"); a.setAppliedAt(LocalDateTime.now());
        appRepo.save(a);
        model.addAttribute("success","Application submitted");
        return "redirect:/jobs/"+jobId;
    }
}
