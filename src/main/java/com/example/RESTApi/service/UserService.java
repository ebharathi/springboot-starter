package com.example.RESTApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import com.example.RESTApi.model.User;
import com.example.RESTApi.repository.UserRepository;
import com.example.RESTApi.utils.JwtUtil;
// password hashing 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    // REGISTRATION METHOD
    public User register(User newUser) throws Exception {

        if (newUser == null) {
            throw new IllegalArgumentException("User Data is required");
        }
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);

    }

    // LOGIN METHOD
    public String login(String email, String password) throws Exception {
        Optional<User> user = findByEmail(email);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                System.out.println("PASSWORD MATCHED & GENERATING TOKEN[+]");
                return jwtUtil.generateToken(email);
            } else {
                throw new Exception("Invalid credentials");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    // OTHER METHODS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}
