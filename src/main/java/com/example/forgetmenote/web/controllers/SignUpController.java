package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.User;
import com.example.forgetmenote.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import com.example.forgetmenote.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SignUpController {

    private final UserRepository userRepository;

    public SignUpController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/signUpForm")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());

        return "signUpForm";
    }

    @PostMapping("/createUser")
    public String createUser(@Valid User user, Model model){
        model.addAttribute("user",user);
        user.getId();
        user.getName();
        user.getUsername();
        user.getPassword();
        user.getEmail();
        userRepository.save(user);
        return "redirect:/home";
    }
}
