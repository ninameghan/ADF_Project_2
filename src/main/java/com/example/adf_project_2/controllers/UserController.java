package com.example.adf_project_2.controllers;

import com.example.adf_project_2.controllers.dtos.NewUserDTO;
import com.example.adf_project_2.controllers.handlers.UserWithEmailAlreadyExistsException;
import com.example.adf_project_2.entities.User;
import com.example.adf_project_2.repositories.IUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    // ENDPOINT: localhost:8080/users
    @GetMapping({"/", ""})
    List<User> findAll() {
        return userRepository.findAll();
    }

    // ENDPOINT: localhost:8080/users
    // localhost:8080/users with JSON
    //{
    //        "userEmail": "john.doe@example.com",
    //        "userPassword": "password123",
    //        "userLocked": false,
    //        "userRole": "USER",
    //        "userPhoneNumber": "123-456-7890",
    //        "userPpsn": "123-45-6789"
    //}
    // returns 200 OK with object
    // Repeating this returns 409 CONFLICT with error message
    // Posting without JSON or invalid JSON returns 400 BAD REQUEST with error message
    @PostMapping({"/", ""})
    User addNewUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        if (userRepository.findUserByEmail(newUserDTO.userEmail()).isPresent()){
            throw new UserWithEmailAlreadyExistsException();
        }
        return userRepository.save(new User(newUserDTO.userEmail(), newUserDTO.userPassword(), newUserDTO.userLocked(),
                newUserDTO.userRole(), newUserDTO.userPhoneNumber(), newUserDTO.userPpsn()));
    }
}