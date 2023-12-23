package com.example.forgetmenote.dto;

import com.example.forgetmenote.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateEventDTO {
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean sendEmailWhenInvited;

    public UserCreateEventDTO(){

    }

    public UserCreateEventDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

}
