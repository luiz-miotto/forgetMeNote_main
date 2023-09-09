package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.EventsWithUsers;
import com.example.forgetmenote.models.User;
import com.example.forgetmenote.repositories.EventsWithUsersRepository;
import com.example.forgetmenote.repositories.UserRepository;
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

    private final UserRepository userRepository;
    private final EventsWithUsersRepository eventsWithUsersRepository;

    public EventCreatorController(EventRepository eventRepository, UserRepository userRepository,
                                  EventsWithUsersRepository eventsWithUsersRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventsWithUsersRepository = eventsWithUsersRepository;
    }

    @GetMapping("/createEvent")
    public String showEventForm(Model model){
        model.addAttribute("event", new Event());
        List<User> userList = userRepository.findAll();
        for (User user: userList){
            System.out.println(user.getUsername());
        }
        model.addAttribute("userList",userList);
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
        EventsWithUsers eventsWithUsers = new EventsWithUsers();
        model.addAttribute("events_with_users", eventsWithUsers);
        eventRepository.save(event);
        System.out.println(event.getName());
        for(User user: event.getAttendees()){
            System.out.println(user.getUsername());
        }
        eventsWithUsers.setEvent_id(event.getId());
        eventsWithUsers.setEvent_name(event.getName());
        eventsWithUsersRepository.save(eventsWithUsers);
        return "redirect:/schedule";
    }



}
