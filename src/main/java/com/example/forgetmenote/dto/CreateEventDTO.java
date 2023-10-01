package com.example.forgetmenote.dto;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateEventDTO {
    private String name;
    private String description;
    private int dateCreated;
    private String scheduledDate;
    private String dueDate;
    private int importance;
    @OneToMany
    private List<User> attendees;

    @Enumerated(EnumType.STRING)
    private Event.EventType eventType;

    public enum EventType{
        WORK_EVENT,
        TASK,
        SOCIAL_EVENT,

    }

    public CreateEventDTO(){
        this.attendees = new ArrayList<>();
    }

    public void setAttendee(User user){
        this.attendees.add(user);
        for(User attendee: attendees){
            System.out.println(attendee.getName());
        }

    }

    public List<User> getAttendees(){
        return this.attendees;
    }
}
