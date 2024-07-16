package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.messaging.MessageDetails;
import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.EventsWithUsers;
import com.example.forgetmenote.models.User;
import com.example.forgetmenote.repositories.EventRepository;
import com.example.forgetmenote.repositories.EventsWithUsersRepository;
import com.example.forgetmenote.repositories.MessageDetailsRepository;
import com.example.forgetmenote.repositories.UserRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Data
public class EventController {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventsWithUsersRepository eventsWithUsersRepository;
    private final MessageDetailsRepository messageDetailsRepository;

    public EventController(EventRepository eventRepository, UserRepository userRepository, EventsWithUsersRepository eventsWithUsersRepository, MessageDetailsRepository messageDetailsRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventsWithUsersRepository = eventsWithUsersRepository;
        this.messageDetailsRepository = messageDetailsRepository;
    }


    @GetMapping("/events")
    public List<Event> getEvent(){
        return (List<Event>) eventRepository.findAll();
    }

    @CrossOrigin(origins = "/eventForm")
    @PostMapping("/events")
    public void addEvent(@RequestBody Event event){

        //event.setEventType(Event.EventType.WORK_EVENT);

        String[] scheduleDateParts = event.getScheduledDate().split("T");
        //event.setScheduledDate(scheduleDateParts[0]);
        event.setScheduledDate(scheduleDateParts[0] + "T" + event.getScheduledTime());
        System.out.println("Event scheduled date " + event.getScheduledDate());
        String[] dueDateParts = event.getDueDate().split("T");
        event.setDueDate(dueDateParts[0]);
        eventRepository.save(event);

        System.out.println(event.getName());
        // For each attendee, we are creating a new record of events_with_users in our database
        saveEventWithUsers(event, event.getReminderScheduled());
    }

    public void saveEventWithUsers(Event event, Boolean sendReminder){
        List<User> userList = new ArrayList<>();
        for(String username: event.getAttendees()){
            EventsWithUsers eventsWithUsers = new EventsWithUsers();
            eventsWithUsers.setEvent_id(event.getId());
            eventsWithUsers.setEvent_name(event.getName());
            eventsWithUsers.setUser_name(username);

            Optional<User> attendee = userRepository.findByUsername(username);
            if(attendee.isPresent()){
                User actualUser = attendee.get();
                eventsWithUsers.setUser_id(actualUser.getId());
                userList.add(actualUser);
            }
            eventsWithUsersRepository.save(eventsWithUsers);
        }
        if(sendReminder == true){
            saveMessage(event,userList);
        }

    }

    public void saveMessage(Event event, List<User> userList){
        for(User user: userList){
            MessageDetails messageDetails = new MessageDetails();
            messageDetails.setSenderId(event.getId());
            messageDetails.setMessageSubject(event.getName());
            messageDetails.setMessageBody(event.getDescription());
            messageDetails.setRecipientEmail(user.getEmail());
            messageDetails.setRecipientId(user.getId());
            messageDetails.setSendTime(event.getScheduledDate()); // will need to correct this line when time is set as well and not just dates
            messageDetails.setEventId(event.getId());
            messageDetailsRepository.save(messageDetails);
        }
    }
}
