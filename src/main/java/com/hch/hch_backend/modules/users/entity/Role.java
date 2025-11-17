package com.hch.hch_backend.modules.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column(nullable = false, unique = true) // cannot be null and must be unique
    private String name; // ADMIN, DOCTOR, PATIENT, STAFF

    private String description;
}
