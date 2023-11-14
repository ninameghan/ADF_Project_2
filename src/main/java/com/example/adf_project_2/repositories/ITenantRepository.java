package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITenantRepository extends JpaRepository<Tenant, Integer> {
}
