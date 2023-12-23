package com.example.forgetmenote.models;

import com.example.forgetmenote.dto.UserCreateEventDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "event_user",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(){

    }

    public User(String name){
        this.name = name;
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this. password = password;
    }
    public User(String name,  String emailAddress, String username, String password){
        this.name = name;
        this.email = emailAddress;
        this.username = username;
        this.password = password;
    }

    public User(UserCreateEventDTO userCreateEventDTO){
        this.name = userCreateEventDTO.getName();
        this.email = userCreateEventDTO.getEmail();
        this.username = userCreateEventDTO.getUsername();
        this.id = userCreateEventDTO.getId();
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

}
