package com.example.forgetmenote.models;

import com.example.forgetmenote.dto.CreateEventDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP) //this tells postgres that I want the date and time stored instead of just DATE or just TIME
    private java.util.Date createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String scheduledDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dueDate;
    private int importance;
    private Boolean active;

    @ElementCollection
    private List<String> attendees;

    @Enumerated(EnumType.STRING)
    private Event.EventType eventType;


    public enum EventType{
        WORK_EVENT,
        SOCIAL_EVENT,
        TASK,

    }

    public Event(){
        this.createdDate = new Date();
        //this.attendees = new ArrayList<>();
        this.active = true;
        this.eventType = EventType.TASK;
    }

    public Event(Event.EventType type){
        this.eventType = type;
    }

    public Event(String name){
        this.name = name;
        //this.attendees = new ArrayList<>();
    }
    public Event(String name, String description){
        this.name = name;
        this.description = description;
        //this.attendees = new ArrayList<>();
    }

    public Event(String name, String description, String scheduledDate){
        this.name = name;
        this.description = description;
        this.scheduledDate = scheduledDate;
    }

    public Event (String name, String description, String scheduledDate, String dueDate, EventType eventType){
        this.name = name;
        this.description = description;
        this.scheduledDate = scheduledDate;
        this.dueDate = dueDate;
        this.eventType = eventType;
    }

    public Event (String name, String description, String scheduledDate, String dueDate, String eventType){
        this.name = name;
        this.description = description;
        this.scheduledDate = scheduledDate;
        this.dueDate = dueDate;
        this.eventType = EventType.WORK_EVENT;
    }

    public Event(CreateEventDTO createEventDTO){
        this.name = createEventDTO.getName();
        this.description = createEventDTO.getDescription();
        this.createdDate = createEventDTO.getCreatedDate();
        this.scheduledDate = createEventDTO.getScheduledDate();
        this.dueDate = createEventDTO.getDueDate();
        this.attendees = createEventDTO.getAttendees();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
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

    public void setAttendee(String username){
        System.out.println("user was added to event");
        //this.attendees.add(username);
    }

    public List<String> getAttendees(){
        return this.attendees;
    }

    public Event.EventType getEventType(){
        return this.eventType;
    }
}
