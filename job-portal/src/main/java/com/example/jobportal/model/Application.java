package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Job job;
    @ManyToOne
    private User applicant;
    private String resumeUrl;
    private String status; // APPLIED, REVIEWED, REJECTED, HIRED
    private LocalDateTime appliedAt;
}
