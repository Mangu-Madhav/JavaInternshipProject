package com.example.jobportal.util;

import com.example.jobportal.model.Role;
import com.example.jobportal.model.User;
import com.example.jobportal.repository.RoleRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    public DataLoader(RoleRepository roleRepo, UserRepository userRepo, BCryptPasswordEncoder encoder){
        this.roleRepo = roleRepo; this.userRepo = userRepo; this.encoder = encoder;
    }
    @Override
    public void run(String... args) throws Exception {
        Role r1 = new Role("ROLE_EMPLOYER");
        Role r2 = new Role("ROLE_APPLICANT");
        roleRepo.save(r1); roleRepo.save(r2);

        if(userRepo.findByEmail("employer@example.com").isEmpty()){
            User u = new User();
            u.setFullName("Alice Employer"); u.setEmail("employer@example.com"); u.setPassword(encoder.encode("Password@123")); u.setCompanyName("Acme Corp"); u.setRoles(Set.of(r1));
            userRepo.save(u);
        }
        if(userRepo.findByEmail("applicant@example.com").isEmpty()){
            User a = new User();
            a.setFullName("Bob Applicant"); a.setEmail("applicant@example.com"); a.setPassword(encoder.encode("Password@123")); a.setRoles(Set.of(r2));
            userRepo.save(a);
        }
    }
}
