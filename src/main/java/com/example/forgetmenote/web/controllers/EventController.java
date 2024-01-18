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

    @PostMapping("/events")
    public void addEvent(@RequestBody Event event){
        eventRepository.save(event);
    }
}
