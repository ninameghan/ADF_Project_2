package com.example.adf_project_2;

import com.example.adf_project_2.entities.Tenant;
import com.example.adf_project_2.repositories.ITenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdfProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(AdfProject2Application.class, args);
    }
}
