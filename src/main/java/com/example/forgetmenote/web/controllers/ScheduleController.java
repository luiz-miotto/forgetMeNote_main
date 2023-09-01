package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.Schedule;
import com.example.forgetmenote.repositories.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ScheduleController {

    private final EventRepository eventRepository;


    public ScheduleController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    @ModelAttribute(name = "schedule")
    public Schedule schedule(){
        return new Schedule();
    }


    @ModelAttribute("event")
    public Event showEvent(){
        return new Event();
    }

    @ModelAttribute("eventList")
    public List<Event> showEventList(){
        List<Event> eventList = eventRepository.findAll();
        return eventList;
    }
    @GetMapping("/schedule")
    public String showScheduleForm(){

        return "schedule";
    }

    //handles the post request and executes the function when hitting the submit form
    //also redirects the user after the form is submitted
    @PostMapping
    public String createSchedule(String name){
        Schedule schedule = new Schedule(name);
        return "redirect:/schedule";
    }



}
