package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.Emailing.EmailDetails;
import com.example.forgetmenote.Emailing.EmailService;
import com.example.forgetmenote.dto.CreateEventDTO;
import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.EventsWithUsers;
import com.example.forgetmenote.models.User;
import com.example.forgetmenote.rabbitmq.RabbitEventMessagingService;
import com.example.forgetmenote.rabbitmq.publisher.RabbitMQProducer;
import com.example.forgetmenote.repositories.EventsWithUsersRepository;
import com.example.forgetmenote.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.forgetmenote.repositories.EventRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class EventCreatorController {
    private final EventRepository eventRepository;

    private final UserRepository userRepository;
    private final EventsWithUsersRepository eventsWithUsersRepository;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EmailService emailService;

    public EventCreatorController(EventRepository eventRepository, UserRepository userRepository,
                                  EventsWithUsersRepository eventsWithUsersRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventsWithUsersRepository = eventsWithUsersRepository;
    }

    @ModelAttribute
    public Event showEvent(){
        return new Event();
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
    //also redirects the user to the schedule page after the form is submitted
    @PostMapping("/createEvent")
    public String createEvent(@Valid Event event, Model model, CreateEventDTO createEventDTO,User user){
        model.addAttribute("event", event);
        Date currentTimeAndDate = new Date();

        eventRepository.save(event);
        System.out.println(event.getName());
        // For each attendee, we are creating a new record of events_with_users in our database
        for(String username: event.getAttendees()){
            EventsWithUsers eventsWithUsers = new EventsWithUsers();
            model.addAttribute("events_with_users", eventsWithUsers);
            eventsWithUsers.setEvent_id(event.getId());
            eventsWithUsers.setEvent_name(event.getName());
            eventsWithUsers.setUser_name(username);

            Optional<User> attendee = userRepository.findByUsername(username);
            if(attendee.isPresent()){
                User actualUser = attendee.get();
                eventsWithUsers.setUser_id(actualUser.getId());
            }
            eventsWithUsersRepository.save(eventsWithUsers);
        }
        //this.rabbitTemplate = new RabbitTemplate();
        //RabbitMQProducer producer = new RabbitMQProducer(rabbitTemplate);
        //producer.sendMessage(event.getName());
      //  RabbitEventMessagingService rabbitEventMessagingService = new RabbitEventMessagingService(rabbitTemplate);
       // rabbitEventMessagingService.sendEventName(event.getName());

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setAttachment("nothing");
        emailDetails.setRecipient("luiz.l.miotto94@gmail.com");
        emailDetails.setSubject(event.getName());
        emailDetails.setMessageBody("Event Description: " + event.getDescription());
        emailService.sendSimpleEmail(emailDetails);
        return "redirect:/schedule";
    }



}
