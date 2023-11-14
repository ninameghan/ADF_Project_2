package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPropertyRepository extends JpaRepository<Property, Integer> {
}
