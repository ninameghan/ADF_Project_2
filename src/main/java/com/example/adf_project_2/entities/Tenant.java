package com.example.adf_project_2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tenant {
    @Id
    @GeneratedValue
    private int tenantId;
    private String tenantName;
    private String tenantPhoneNumber;

    public Tenant(String tenantName, String tenantPhoneNumber) {
        this.tenantName = tenantName;
        this.tenantPhoneNumber = tenantPhoneNumber;
    }
}
