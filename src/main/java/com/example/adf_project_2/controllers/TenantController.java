package com.example.adf_project_2.controllers;

import com.example.adf_project_2.controllers.dtos.NewPropertyDTO;
import com.example.adf_project_2.controllers.dtos.NewTenantDTO;
import com.example.adf_project_2.controllers.handlers.PropertyNoAvailabilityException;
import com.example.adf_project_2.controllers.handlers.ResourceNotFoundException;
import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.IPropertyRepository;
import com.example.adf_project_2.repositories.ITenantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {

    @Autowired
    private ITenantRepository tenantRepository;

    @Autowired
    private IPropertyRepository propertyRepository;

    // ENDPOINT: localhost:8080/tenants
    @GetMapping({"/", ""})
    List<Tenant> findAll(){
        return tenantRepository.findAll();
    }

    // ENDPOINT: localhost:8080/tenants/{id}
    // localhost:8080/tenants/1 returns 200 OK with object
    // localhost:8080/tenants/100 returns 404 NOT FOUND with error message
    @GetMapping("/{id}")
    Tenant findById(@PathVariable("id") int tenantId){
        Optional<Tenant> tenantOp = tenantRepository.findById(tenantId);
        if (tenantOp.isPresent()){
            return tenantOp.get();
        }
        throw new ResourceNotFoundException("Tenant with ID: " + tenantId + " was not found!");
    }

    // ENDPOINT: localhost:8080/tenants/{id}
    // localhost:8080/tenants/1 returns 204 NO CONTENT
    // localhost:8080/tenants/100 returns 404 NOT FOUND with error message
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") int tenantId){
        if (tenantRepository.existsById(tenantId)){
            tenantRepository.deleteById(tenantId);
        } else {
            throw new ResourceNotFoundException("Tenant with ID: " + tenantId + " was not found!");
        }
    }

    // ENDPOINT: localhost:8080/properties
    // localhost:8080/properties with JSON
    //{
    //  "tenantName": "John Doe",
    //  "tenantEmail": "john.doe@example.com",
    //  "tenantPhoneNumber": "123-456-7890",
    //  "propertyId": 2
    //}
    // returns 200 OK with object
    // Repeating this returns 409 CONFLICT with error message (tenant with email or phone number already exists)
    // Posting without JSON or invalid JSON returns 400 BAD REQUEST with error message
    // Posting with "propertyId": 1 returns 400 BAD REQUEST with error message (property has no availability)
    // Posting with "propertyId": 6 returns 404 NOT FOUND with error message (property does not exist)
    @PostMapping({"/", ""})
    Tenant addNewTenant(@Valid @RequestBody NewTenantDTO newTenantDTO) {
        Optional<Property> propertyOp = propertyRepository.findById(newTenantDTO.propertyId());
        if (propertyOp.isPresent()){
            if (propertyRepository.propertyHasAvailability(newTenantDTO.propertyId())){
                return tenantRepository.save(new Tenant(newTenantDTO.tenantName(),
                        newTenantDTO.tenantEmail(), newTenantDTO.tenantPhoneNumber(), propertyOp.get()));
            } else {
                throw new PropertyNoAvailabilityException("Property with ID " + newTenantDTO.propertyId() + " has no availability!");
            }
        }
        throw new ResourceNotFoundException("Property with ID " + newTenantDTO.propertyId() + " does not exist!");
    }

    //TODO: Move a tenant
}
