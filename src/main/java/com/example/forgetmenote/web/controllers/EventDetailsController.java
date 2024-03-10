package com.example.forgetmenote.web.controllers;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.Schedule;
import com.example.forgetmenote.repositories.EventRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class EventDetailsController {

    private final EventRepository eventRepository;

    public EventDetailsController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @ModelAttribute(name = "schedule")
    public Schedule schedule(){
        return new Schedule();
    }

    /*

    @ModelAttribute(name = "event")
    public EventInterface event(String longId){
        Optional<EventInterface> event = eventRepository.findById(longId);
         EventInterface actualEvent = event.get();
        return actualEvent;
    }

     */

    @RequestMapping(value = "/eventDetails", method = GET)
    public String getEventDetails(@RequestParam("id") @PathVariable String id, Model model, HttpSession httpSession){
        long longId = Long.parseLong(id);
        Optional<Event> event = eventRepository.findById(longId);
        Event actualEvent = event.get();
        httpSession.setAttribute("sessionEvent", actualEvent);
        model.addAttribute("event",actualEvent);
        return "eventDetails";
    }

    @PostMapping("/cancelEvent")
    public String cancelEvent(Model model, HttpSession httpSession){
       // EventInterface actualEvent = (EventInterface)model.asMap().get("event");
        Event actualEvent = Event.class.cast(httpSession.getAttribute("sessionEvent"));
       System.out.println("here is the post event " + actualEvent.getName());
        actualEvent.setActive(false);
        eventRepository.save(actualEvent);

        String id = Long.toString(actualEvent.getId());
        //return "/eventDetails" + "?id="+ id;
        //return "/eventDetails" or return "redirect:/eventDetails" to have it redirect to update task (getting 500 and 400 error with these methods)
       return "redirect:/schedule";
    }

    @PostMapping("/activateEvent")
    public String activateEvent(Model model, HttpSession httpSession){
        // EventInterface actualEvent = (EventInterface)model.asMap().get("event");
        Event actualEvent = Event.class.cast(httpSession.getAttribute("sessionEvent"));
       System.out.println("here is the post event " + actualEvent.getName());
        System.out.println("here is the id " + actualEvent.getId());
        actualEvent.setActive(true);
        eventRepository.save(actualEvent);
        //return "/eventDetails" or return "redirect:/eventDetails"
        return "redirect:/schedule";
    }



}
