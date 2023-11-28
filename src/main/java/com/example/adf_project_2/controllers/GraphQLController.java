package com.example.adf_project_2.controllers;

import com.example.adf_project_2.controllers.handlers.PropertyNoAvailabilityException;
import com.example.adf_project_2.controllers.handlers.ResourceNotFoundException;
import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.IPropertyRepository;
import com.example.adf_project_2.repositories.ITenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {

    @Autowired
    private IPropertyRepository propertyRepository;

    @Autowired
    private ITenantRepository tenantRepository;

    @QueryMapping(value = "properties")
    List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    @QueryMapping
    Property findProperty(@Argument int id){
        return propertyRepository.findById(id).orElse(null);
    }

    @MutationMapping
    Tenant createTenant(@Argument("tenantName") String tenantName, @Argument("tenantEmail") String tenantEmail,
                            @Argument("tenantPhoneNumber") String tenantPhoneNumber, @Argument("propertyId") int propertyId){
        Optional<Property> propertyOp = propertyRepository.findById(propertyId);
        if (propertyOp.isPresent()) {
            if (propertyRepository.propertyHasAvailability(propertyId)) {
                return tenantRepository.save(new Tenant(tenantName, tenantEmail, tenantPhoneNumber, propertyOp.get()));
            } else {
                throw new PropertyNoAvailabilityException("Property with ID " + propertyId + " has no availability!");
            }
        }
        throw new ResourceNotFoundException("Property with ID " + propertyId + " does not exist!");
    }

    @MutationMapping
    Boolean deleteProperty(@Argument("propertyId") int propertyId){
        if (propertyRepository.existsById(propertyId)){
            propertyRepository.deleteById(propertyId);
            return Boolean.TRUE;
        } else {
            throw new ResourceNotFoundException("Property with ID: " + propertyId + " was not found!");
        }
    }
}
