package com.example.adf_project_2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int propertyId;
    @Column(name = "address", unique = true, nullable = false)
    private String propertyAddress;
    @Column(name = "eircode", unique = true, nullable = false)
    private String propertyEircode;
    @Column(name = "capacity", nullable = false)
    private int propertyCapacity;
    @Column(name = "cost", nullable = false)
    private double propertyMonthlyCost;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Tenant> tenants;

    public Property(String propertyAddress, String propertyEircode, int propertyCapacity, double propertyMonthlyCost) {
        this.propertyAddress = propertyAddress;
        this.propertyEircode = propertyEircode;
        this.propertyCapacity = propertyCapacity;
        this.propertyMonthlyCost = propertyMonthlyCost;
    }
}
