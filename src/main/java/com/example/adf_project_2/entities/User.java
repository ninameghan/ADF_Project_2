package com.example.adf_project_2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email", unique = true)
    private String userEmail;
    @Column(name = "password", nullable = false)
    private String userPassword;
    @Column(name = "locked", nullable = false)
    private boolean userLocked;
    @Column(name = "role", nullable = false)
    private String userRole;
    @Column(name = "phoneNumber", nullable = false)
    private String userPhoneNumber;
    @Column(name = "ppsn", unique = true, nullable = false)
    private String userPpsn;
}
