package com.example.adf_project_2.repositories;

import com.example.adf_project_2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userEmail=:userEmail")
    Optional<User> findUserByEmail(@Param("userEmail") String userEmail);
}
