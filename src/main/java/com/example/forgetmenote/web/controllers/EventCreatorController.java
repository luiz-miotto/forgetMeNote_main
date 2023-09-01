package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.Event;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.forgetmenote.repositories.EventRepository;

import java.util.List;

@Controller
public class EventCreatorController {
    private final EventRepository eventRepository;


    public EventCreatorController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping("/createEvent")
    public String showEventForm(Model model){
        model.addAttribute("event", new Event());
        List<Event> eventList = eventRepository.findAll();
        for(Event event: eventList ){
            System.out.println(event.getName());
        }
        return "createEvent";
    }


    //handles the post request and executes the function when hitting the submit form
    //also redirects the user after the form is submitted
    @PostMapping("/createEvent")
    public String createEvent(@Valid Event event, Model model){
        model.addAttribute("event", event);
        eventRepository.save(event);
        return "redirect:/schedule";
    }



}
