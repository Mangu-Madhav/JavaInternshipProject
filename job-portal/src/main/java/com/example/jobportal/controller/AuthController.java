package com.example.jobportal.controller;

import com.example.jobportal.model.Role;
import com.example.jobportal.model.User;
import com.example.jobportal.repository.RoleRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AuthController {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final BCryptPasswordEncoder encoder;
    public AuthController(UserRepository userRepo, RoleRepository roleRepo, BCryptPasswordEncoder encoder){
        this.userRepo = userRepo; this.roleRepo = roleRepo; this.encoder = encoder;
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/register")
    public String registerForm(){ return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam String fullName, @RequestParam String email,
                           @RequestParam String password, @RequestParam String role,
                           @RequestParam(required=false) String companyName, Model model){
        if(userRepo.existsByEmail(email)){
            model.addAttribute("error", "Email already registered. Try logging in.");
            return "register";
        }
        User u = new User();
        u.setFullName(fullName); u.setEmail(email); u.setPassword(encoder.encode(password)); u.setCompanyName(companyName);
        Role r = roleRepo.findById(role).orElse(new Role(role));
        roleRepo.save(r);
        u.setRoles(Set.of(r));
        userRepo.save(u);
        model.addAttribute("success", "Registered. Please login.");
        return "login";
    }
}
