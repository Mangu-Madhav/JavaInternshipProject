package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="jobs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;
    private String location;
    private boolean remoteJob;
    private String jobType;
    private Integer salaryMin;
    private Integer salaryMax;
    private LocalDateTime postedAt;
    @ManyToOne
    private User employer;
}
