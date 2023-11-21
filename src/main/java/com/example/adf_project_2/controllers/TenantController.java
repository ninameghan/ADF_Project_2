package com.example.adf_project_2.controllers;

import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.ITenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
