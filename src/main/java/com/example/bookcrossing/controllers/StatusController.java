package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.*;
import com.example.bookcrossing.repo.LanguageRepository;
import com.example.bookcrossing.repo.StatusRepository;
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
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;

    @GetMapping("/status")
    public String statusMain( Model model){
        Iterable<Status> statuses = statusRepository.findAll();
        model.addAttribute("statuses", statuses);
        return "/status/status-main";
    }

    @GetMapping("/status/add")
    public String Main(Status status, Model model){
        Iterable<Status> statuses = statusRepository.findAll();
        model.addAttribute("statuses",statuses);
        return "/status/status-add";
    }

    @PostMapping("/status/add")
    public String statusAdd( @RequestParam String title,
                              Model model)
    {
        Status status = new Status(title);
        statusRepository.save(status);
        return "redirect:/status";
    }

    @GetMapping("/status/{id}/edit")
    public String statusEdit(@PathVariable("id")long id,
                           Model model)
    {
        if(!statusRepository.existsById(id)){
            return "redirect:/status";
        }
        Optional<Status> post = statusRepository.findById(id);
        ArrayList<Status> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("status",res);
        return "/status/status-edit";
    }
    @PostMapping("/status/{id}/edit")
    public String statusUpdate(@PathVariable("id")long id,
                             @RequestParam String title,
                             Model model)
    {
        Status status = statusRepository.findById(id).orElseThrow();
        status.setTitle(title);
        statusRepository.save(status);
        return "redirect:/status";
    }

    @PostMapping("/status/{id}/remove")
    public String statusDelete(@PathVariable("id") long id, Model model){
        Status status = statusRepository.findById(id).orElseThrow();
        statusRepository.delete(status);
        return "redirect:/status";
    }

}
