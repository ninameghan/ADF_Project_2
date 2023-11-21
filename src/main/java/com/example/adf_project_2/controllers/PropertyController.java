package com.example.adf_project_2.controllers;

import com.example.adf_project_2.entities.Property;
import com.example.adf_project_2.repositories.IPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}