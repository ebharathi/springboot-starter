package com.example.RESTApi.controller;

import com.example.RESTApi.model.User;
import com.example.RESTApi.service.UserService;
import com.example.RESTApi.utils.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("CALLING ALL USERS[+]");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        System.out.println("CALLING USER BY ID[+]");
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<User>> registerUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.register(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage<String>> loginUser(@RequestParam String email,
            @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.login(email, password));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(new ResponseMessage<>(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage<>(false, e.getMessage()));
        }
    }

}
