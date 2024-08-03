package com.example.RESTApi.repository;

import com.example.RESTApi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
