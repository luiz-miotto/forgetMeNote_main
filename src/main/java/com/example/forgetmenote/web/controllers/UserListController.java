package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.User;
import com.example.forgetmenote.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserListController {

    private final UserRepository userRepository;

    public UserListController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/getUserList")
    public List<User> getUserList(){
        return (List<User>) userRepository.findAll();
    }
}
