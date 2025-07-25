package com.hrms.repository;

import com.hrms.model.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // You can add custom queries here if needed
     List<User> findByRole(String role);
     List<User> findByRoleIgnoreCase(String role);
}
