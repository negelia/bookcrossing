package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.Event;
import com.example.bookcrossing.models.News;
import com.example.bookcrossing.models.Review;
import com.example.bookcrossing.models.User;
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
public class NewsController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/news")
    public String Main( Model model){
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);

        return "/news/news-main";
    }

    @GetMapping("/news/add")
    public String newsAddMain(News news, Model model){
        Iterable<News> news1 = newsRepository.findAll();
        model.addAttribute("news1",news1);

        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "/news/news-add";
    }

    @PostMapping("/news/add")
    public String newsAdd(@RequestParam String title,
                            @RequestParam String date,
                            @RequestParam String text,
                            @RequestParam String event,
                            Model model)
    {
        Event event1 = eventRepository.findByTitle(event);
        News news = new News(title, date, text, event1);
        newsRepository.save(news);
        return "redirect:/news";
    }

    @GetMapping("/news/{id}/edit")
    public String newsEdit(@PathVariable("id")long id,
                            Model model)
    {
        if(!newsRepository.existsById(id)){
            return "redirect:/news";
        }
        Optional<News> post = newsRepository.findById(id);
        ArrayList<News> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("news",res);

        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);


        return "/news/news-edit";
    }
    @PostMapping("/news/{id}/edit")
    public String newsUpdate(@PathVariable("id")long id,
                              @RequestParam String title,
                              @RequestParam String date,
                              @RequestParam String text,
                              @RequestParam String event,
                              Model model)
    {
        News news = newsRepository.findById(id).orElseThrow();

        Event event1 = eventRepository.findByTitle(event);
        System.out.println(event1.getId());

        news.setTitle(title);
        news.setDate(date);
        news.setText(text);
        news.setEvent(event1);

        newsRepository.save(news);
        return "redirect:/news";
    }

    @PostMapping("/news/{id}/remove")
    public String newsDelete(@PathVariable("id") long id, Model model){
        News news = newsRepository.findById(id).orElseThrow();
        newsRepository.delete(news);
        return "redirect:/news";
    }
}
