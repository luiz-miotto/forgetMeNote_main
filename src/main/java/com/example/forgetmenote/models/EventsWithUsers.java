package com.example.forgetmenote.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events_with_users")
public class EventsWithUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long event_id;
    private String event_name;
    private long user_id;
    private String user_name;

    public EventsWithUsers(){

    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
