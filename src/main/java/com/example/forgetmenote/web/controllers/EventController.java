package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.repositories.EventRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Data
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }


    @GetMapping("/events")
    public List<Event> getEvent(){
        return (List<Event>) eventRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/addEvent")
    @PostMapping("/events")
    public void addEvent(@RequestBody Event event){
        System.out.println("Event scheduled date here:" + event.getScheduledDate());
        String[] scheduleDateParts = event.getScheduledDate().split("T");
        event.setScheduledDate(scheduleDateParts[0]);
        String[] dueDateParts = event.getDueDate().split("T");
        event.setDueDate(dueDateParts[0]);
        eventRepository.save(event);
    }
}
