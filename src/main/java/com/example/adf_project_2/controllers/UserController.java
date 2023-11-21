package com.example.adf_project_2.controllers;

import com.example.adf_project_2.entities.User;
import com.example.adf_project_2.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    // ENDPOINT: localhost:8080/users
    @GetMapping({"/", ""})
    List<User> findAll(){
        return userRepository.findAll();
    }
}