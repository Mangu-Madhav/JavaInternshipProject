package com.example.jobportal.controller;

import com.example.jobportal.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final JobRepository jobRepo;
    public HomeController(JobRepository jobRepo){ this.jobRepo = jobRepo; }

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(required=false) String q, Model model){
        if(q==null || q.isBlank()) model.addAttribute("jobs", jobRepo.findAll());
        else model.addAttribute("jobs", jobRepo.findByTitleContainingIgnoreCase(q));
        model.addAttribute("q", q);
        return "index";
    }
}
