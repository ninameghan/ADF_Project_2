package com.example.adf_project_2.controllers;

import com.example.adf_project_2.controllers.handlers.ResourceNotFoundException;
import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.ITenantRepository;
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

    //TODO: Add new tenant and move into a property (subject to capacity)
    // if the house is full, tenant should not be added to database

    //TODO: Move a tenant
}
