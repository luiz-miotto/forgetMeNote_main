package com.example.forgetmenote;


import com.example.forgetmenote.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
//@RequestMapping("/simpleEvent")
//@SessionAttributes("event")
public class EventSimpleCreator {





    @GetMapping("/simpleEvent")
    public String showEventForm(Model model){
        model.addAttribute("event", new Event());
        return "simpleEvent";
    }

    @PostMapping("/simpleEvent")
    public String createEvent(@ModelAttribute Event event, Model model){
        model.addAttribute("event", event);
        System.out.println(event.getName());
        return "/simpleEvent";
    }

    //code below is here just because it was the first event creator controller but seems to be useless now
    @ModelAttribute(name = "eventTypeList")
    public List<Event> eventTypeList(Model model){
        List<Event> eventTypeList = Stream.of(new Event(Event.EventType.SOCIAL_EVENT), new Event(Event.EventType.WORK_EVENT), new Event(Event.EventType.TASK)).collect(Collectors.toList());
        for(Event event: eventTypeList){
            model.addAttribute(event.getEventType());
        }
        return eventTypeList;
    }

}
