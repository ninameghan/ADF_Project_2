package com.example.adf_project_2.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserWithEmailAlreadyExistsException extends RuntimeException{
}
