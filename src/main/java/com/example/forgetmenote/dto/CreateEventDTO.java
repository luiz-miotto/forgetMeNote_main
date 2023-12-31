package com.example.forgetmenote.dto;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CreateEventDTO {
    private String name;
    private String description;
    private Date createdDate;
    private String scheduledDate;
    private String dueDate;
    private int importance;
    @OneToMany
    private List<String> attendees;

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

    public CreateEventDTO(Event event){
        this.name = event.getName();
        this.description = event.getDescription();
        this.createdDate = event.getCreatedDate();
        this.scheduledDate = event.getScheduledDate();
        this.dueDate = event.getDueDate();
        this.attendees = event.getAttendees();

    }

    public void setAttendee(String user){
        this.attendees.add(user);

    }

    public List<String> getAttendees(){
        return this.attendees;
    }
}
