package com.example.RESTApi.repository;

import com.example.RESTApi.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // own query method creation
    public Optional<User> findByEmail(String email);

    public Optional<User> findByName(String name);
}
