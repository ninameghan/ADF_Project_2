package com.example.adf_project_2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int tenantId;
    @Column(name = "name", nullable = false)
    private String tenantName;
    @Column(name = "email", unique = true, nullable = false)
    private String tenantEmail;
    @Column(name = "phoneNumber", unique = true, nullable = false)
    private String tenantPhoneNumber;

    public Tenant(String tenantName, String tenantEmail, String tenantPhoneNumber) {
        this.tenantName = tenantName;
        this.tenantEmail = tenantEmail;
        this.tenantPhoneNumber = tenantPhoneNumber;
    }
}
