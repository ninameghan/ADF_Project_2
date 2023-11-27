package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPropertyRepository extends JpaRepository<Property, Integer> {

    @Query("select p from Property p where size(p.tenants) = 0")
    List<Property> findAllEmptyProperties();

    @Query("select p from Property p where size(p.tenants) < p.propertyCapacity")
    List<Property> findAllPropertiesWithAvailability();

    @Query("select case when (size(p.tenants) < p.propertyCapacity) then true else false end from Property p where p.propertyId=:id")
    boolean propertyHasAvailability(@Param("id") int propertyId);

    @Query("select new com.example.adf_project_2.repositories.PropertyAndTenantCount(p.propertyId, size(p.tenants)) from Property p where p.propertyId=:id")
    Optional<PropertyAndTenantCount> findPropertyAndTenantCount(@Param("id") int propertyId);

    @Query("select p.tenants from Property p where p.propertyId=:id")
    List<Tenant> findAllTenantsForProperty(@Param("id") int propertyId);

    @Query("select new com.example.adf_project_2.repositories.PropertyAndTotalRentalIncome(p.propertyId, (p.propertyMonthlyCost * size(p.tenants))) from Property p where size(p.tenants) > 0")
    List<PropertyAndTotalRentalIncome> findTotalRentalIncomesOfOccupiedProperties();
}
