package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UsersService;



@RestController
public class UserController {

  @Autowired
  UserRepository userRepo;
  @PostMapping(path="/register")
	public ResponseEntity<String> register(@Valid @RequestBody User user){
    userRepo.save(user);
    return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);

  }
  
  @GetMapping(path="/user/{id}")
  @PreAuthorize("hasRole('USER')") 
	public User register(@PathVariable int id){
    Optional<User> userOpt =userRepo.findById(id);
    return userOpt.get();
  }
}


