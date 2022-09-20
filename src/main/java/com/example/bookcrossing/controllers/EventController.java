package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.*;
import com.example.bookcrossing.repo.EventRepository;
import com.example.bookcrossing.repo.NewsRepository;
import com.example.bookcrossing.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/event")
    public String Main( Model model){
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "/event/event-main";
    }

    @GetMapping("/event/add")
    private String eventAddMain(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);

        return "/event/event-add";
    }

    @PostMapping("/event/add")
    public String eventAdd(     @RequestParam String title,
                                @RequestParam String date,
                                @RequestParam Boolean proved,
                                Model model)
    {
        Event event = new Event(title, date, proved);
        eventRepository.save(event);

        return "redirect:/event";
    }

    @GetMapping("/event/{id}/edit")
    public String eventEdit(@PathVariable("id")long id,
                           Model model)
    {
        if(!eventRepository.existsById(id)){
            return "redirect:/event";
        }
        Optional<Event> post = eventRepository.findById(id);
        ArrayList<Event> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("event",res);

        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);


        return "/event/event-edit";
    }
    @PostMapping("/event/{id}/edit")
    public String eventUpdate(@PathVariable("id")long id,
                             @RequestParam String title,
                             @RequestParam String date,
                             @RequestParam Boolean proved,
                             Model model)
    {
        Event event = eventRepository.findById(id).orElseThrow();

        event.setTitle(title);
        event.setDate(date);
        event.setProved(proved);

        eventRepository.save(event);
        return "redirect:/event";
    }

    @PostMapping("/event/{id}/remove")
    public String eventDelete(@PathVariable("id") long id, Model model){
        Event event = eventRepository.findById(id).orElseThrow();
        eventRepository.delete(event);
        return "redirect:/event";
    }

}
