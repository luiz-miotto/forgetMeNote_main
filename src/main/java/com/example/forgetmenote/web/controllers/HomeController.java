package com.example.forgetmenote.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        Date currentTimeAndDate = new Date();
        System.out.println("01271994");
        System.out.println(currentTimeAndDate + "01271994");
        return "home";
    }
}
