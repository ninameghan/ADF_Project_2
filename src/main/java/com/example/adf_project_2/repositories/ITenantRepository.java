package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITenantRepository extends JpaRepository<Tenant, Integer> {

    @Modifying
    @Transactional
    @Query("update Tenant t set t.property=:newProperty where t.tenantId=:id")
    void moveTenant(@Param("id") int tenantId, @Param("newProperty") Property newProperty);
}
