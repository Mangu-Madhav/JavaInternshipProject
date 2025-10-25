package com.example.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Role {
    @Id
    private String name; // "ROLE_EMPLOYER" or "ROLE_APPLICANT"
}
