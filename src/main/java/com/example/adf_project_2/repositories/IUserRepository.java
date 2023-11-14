package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
}
