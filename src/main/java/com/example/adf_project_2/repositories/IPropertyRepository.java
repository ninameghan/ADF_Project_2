package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPropertyRepository extends JpaRepository<Property, Integer> {

    @Query("select p from Property p where size(p.tenants) = 0")
    List<Property> findAllEmptyProperties();

    @Query("select p from Property p where size(p.tenants) < p.propertyCapacity")
    List<Property> findAllPropertiesWithAvailability();
}
