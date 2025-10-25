package com.example.jobportal.controller;

import com.example.jobportal.model.Job;
import com.example.jobportal.model.User;
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
@RequestMapping("/jobs")
public class JobController {
    private final JobRepository jobRepo;
    private final UserRepository userRepo;
    public JobController(JobRepository jobRepo, UserRepository userRepo){ this.jobRepo = jobRepo; this.userRepo = userRepo; }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model){
        Optional<Job> j = jobRepo.findById(id);
        if(j.isEmpty()) return "redirect:/";
        model.addAttribute("job", j.get());
        return "job-details";
    }

    @GetMapping("/post")
    public String postForm() { return "post-job"; }

    @PostMapping("/post")
    public String post(@RequestParam String title, @RequestParam String description,
                       @RequestParam String location, @RequestParam(defaultValue="false") boolean remote,
                       @RequestParam String jobType, @AuthenticationPrincipal UserDetails ud){
        if(ud==null) return "redirect:/login";
        User u = userRepo.findByEmail(ud.getUsername()).orElse(null);
        Job j = new Job();
        j.setTitle(title); j.setDescription(description); j.setLocation(location);
        j.setRemoteJob(remote); j.setJobType(jobType); j.setPostedAt(LocalDateTime.now()); j.setEmployer(u);
        jobRepo.save(j);
        return "redirect:/";
    }
}
