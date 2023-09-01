package com.example.forgetmenote.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private int dateCreated;
    private String scheduledDate;
    private String dueDate;
    private int importance;

    @Enumerated(EnumType.STRING)
    private Event.EventType eventType;

    public enum EventType{
        WORK_EVENT,
        TASK,
        SOCIAL_EVENT,

    }
    public Event(){
        System.out.println("even with no name was created");
        this.dateCreated = 0;
    }

    public Event(Event.EventType type){
        this.eventType = type;
    }

    public Event(String name){
        this.name = name;
        System.out.println("event WITH a name was created");
    }
    public Event(String name, String description){
        this.name = name;
        this.description = description;
        System.out.println("event with a name and description was created");
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
        System.out.println("name was set");
    }


    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Event.EventType getEventType(){
        return this.eventType;
    }
}
