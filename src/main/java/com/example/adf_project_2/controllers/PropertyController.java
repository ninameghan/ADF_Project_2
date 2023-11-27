package com.example.adf_project_2.controllers;

import com.example.adf_project_2.controllers.dtos.NewPropertyDTO;
import com.example.adf_project_2.controllers.dtos.NewRentDTO;
import com.example.adf_project_2.controllers.handlers.ResourceNotFoundException;
import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.IPropertyRepository;
import com.example.adf_project_2.repositories.PropertyAndTenantCount;
import com.example.adf_project_2.repositories.PropertyAndTotalRentalIncome;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    // localhost:8080/properties/1 returns 200 OK with object
    // localhost:8080/properties/100 returns 404 NOT FOUND with error message
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
    // localhost:8080/properties/1/tenants returns 200 OK with list of tenants
    // localhost:8080/properties/100/tenants returns 200 OK with empty list
    @GetMapping("/{id}/tenants")
    List<Tenant> findAllTenantsForProperty(@PathVariable("id") int propertyId) {
        return propertyRepository.findAllTenantsForProperty(propertyId);
    }

    // ENDPOINT: localhost:8080/properties/{id}/tenantcount
    // localhost:8080/properties/1/tenantcount returns 200 OK with object
    // localhost:8080/properties/100/tenantcount returns 404 NOT FOUND with error message
    @GetMapping("/{id}/tenantcount")
    PropertyAndTenantCount findByIdWithTenantCount(@PathVariable("id") int propertyId){
        Optional<PropertyAndTenantCount> propertyOp = propertyRepository.findPropertyAndTenantCount(propertyId);
        if (propertyOp.isPresent()){
            return propertyOp.get();
        }
        throw new ResourceNotFoundException("Property with ID: " + propertyId + " was not found!");
    }

    // ENDPOINT: localhost:8080/properties/rentalincomes
    @GetMapping("/rentalincomes")
    List<PropertyAndTotalRentalIncome> findTotalRentalIncomesOfOccupiedProperties(){
        return propertyRepository.findTotalRentalIncomesOfOccupiedProperties();
    }

    // ENDPOINT: localhost:8080/properties/{id}
    // localhost:8080/properties/1 returns 204 NO CONTENT
    // localhost:8080/properties/100 returns 404 NOT FOUND with error message
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") int propertyId){
        if (propertyRepository.existsById(propertyId)){
            propertyRepository.deleteById(propertyId);
        } else {
            throw new ResourceNotFoundException("Property with ID: " + propertyId + " was not found!");
        }
    }

    // ENDPOINT: localhost:8080/properties
    // localhost:8080/properties with JSON
    // {
    //    "propertyAddress": "123 West Street",
    //    "propertyEircode": "D01ABCD",
    //    "propertyCapacity": 4,
    //    "propertyMonthlyCost": 1500.0
    //}
    // returns 200 OK with object
    // Repeating this returns 409 CONFLICT with error message
    // Posting without JSON or invalid JSON returns 400 BAD REQUEST with error message
    @PostMapping({"/", ""})
    Property addNewProperty(@Valid @RequestBody NewPropertyDTO newPropertyDTO) {
        return propertyRepository.save(new Property(newPropertyDTO.propertyAddress(),
                newPropertyDTO.propertyEircode(), newPropertyDTO.propertyCapacity(), newPropertyDTO.propertyMonthlyCost()));
    }

    // ENDPOINT: localhost:8080/properties/{id}/rent
    // localhost:8080/properties/1/rent with JSON
    //{
    //    "newRent": 2000.0
    //}
    // returns 200 OK with object
    // Posting without JSON or invalid JSON returns 400 BAD REQUEST with error message
    // localhost:8080/properties/100/rent returns 404 NOT FOUND with error message
    @PatchMapping("/{id}/rent")
    Property updateRent(@Valid @RequestBody NewRentDTO newRentDTO, @PathVariable("id") int propertyId){
        if (propertyRepository.existsById(propertyId)){
            propertyRepository.updatePropertyRent(propertyId, newRentDTO.newRent());
            return propertyRepository.findById(propertyId).get();
        }
        throw new ResourceNotFoundException("Property with ID: " + propertyId + " was not found!");
    }
}
