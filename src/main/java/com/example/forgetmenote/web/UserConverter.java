package com.example.forgetmenote.web;

import com.example.forgetmenote.models.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, User> {

    @Override
    public User convert(String username) {
        System.out.println("Trying to conver username=" + username + "into a user");
        return null;
    }
}
