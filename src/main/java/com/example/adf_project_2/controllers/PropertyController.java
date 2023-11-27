package com.example.adf_project_2.controllers;

import com.example.adf_project_2.controllers.handlers.ResourceNotFoundException;
import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.IPropertyRepository;
import com.example.adf_project_2.repositories.PropertyAndTenantCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private IPropertyRepository propertyRepository;

    // ENDPOINT: localhost:8080/properties
    @GetMapping({"/", ""})
    List<Property> findAll(){
        return propertyRepository.findAll();
    }

    // ENDPOINT: localhost:8080/properties/{id}
    @GetMapping("/{id}")
    Property findById(@PathVariable("id") int propertyId){
        Optional<Property> propertyOp = propertyRepository.findById(propertyId);
        if (propertyOp.isPresent()){
            return propertyOp.get();
        }
        throw new ResourceNotFoundException("Property with ID: " + propertyId + " was not found!");
    }

    // ENDPOINT: localhost:8080/properties/available
    @GetMapping("/available")
    List<Property> findAllAvailable(){
        return propertyRepository.findAllPropertiesWithAvailability();
    }

    // ENDPOINT: localhost:8080/properties/empty
    @GetMapping("/empty")
    List<Property> findAllEmpty(){
        return propertyRepository.findAllEmptyProperties();
    }

    // ENDPOINT: localhost:8080/properties/{id}/tenants
    @GetMapping("/{id}/tenants")
    List<Tenant> findAllTenantsForProperty(@PathVariable("id") int propertyId) {
        return propertyRepository.findAllTenantsForProperty(propertyId);
    }

    // ENDPOINT: localhost:8080/properties/{id}/tenantcount
    @GetMapping("/{id}/tenantcount")
    PropertyAndTenantCount findByIdWithTenantCount(@PathVariable("id") int propertyId){
        Optional<PropertyAndTenantCount> propertyOp = propertyRepository.findPropertyAndTenantCount(propertyId);
        if (propertyOp.isPresent()){
            return propertyOp.get();
        }
        throw new ResourceNotFoundException("Property with ID: " + propertyId + " was not found!");

    }

    //TODO: Get total rental income of all occupied properties

    //TODO: Delete property

    //TODO: Change rent of property
}
