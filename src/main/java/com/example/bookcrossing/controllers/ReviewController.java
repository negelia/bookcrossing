package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.*;
import com.example.bookcrossing.repo.BookRepository;
import com.example.bookcrossing.repo.EventRepository;
import com.example.bookcrossing.repo.NewsRepository;
import com.example.bookcrossing.repo.ReviewRepository;
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
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/review")
    public String reviewMain( Model model){
        Iterable<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);

        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        return "/review/review-main";
    }

    @GetMapping("/review/add")
    public String Main(Review review, Model model){
        Iterable<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews",reviews);
        return "/review/review-add";
    }

    @PostMapping("/review/add")
    public String reviewAdd(@RequestParam String title,
                            @RequestParam String pros,
                            @RequestParam String cons,
                            Model model)
    {
        Review review = new Review(title, pros, cons);
        reviewRepository.save(review);
        return "redirect:/review";
    }

    @GetMapping("/review/{id}/edit")
    public String reviewEdit(@PathVariable("id")long id,
                             Model model)
    {
        if(!reviewRepository.existsById(id)){
            return "redirect:/review";
        }
        Optional<Review> post = reviewRepository.findById(id);
        ArrayList<Review> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("review",res);
        return "/review/review-edit";
    }
    @PostMapping("/review/{id}/edit")
    public String reviewUpdate(@PathVariable("id")long id,
                               @RequestParam String title,
                               Model model)
    {
        Review review = reviewRepository.findById(id).orElseThrow();
        review.setTitle(title);
        reviewRepository.save(review);
        return "redirect:/review";
    }

    @PostMapping("/review/{id}/remove")
    public String reviewDelete(@PathVariable("id") long id, Model model){
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
        return "redirect:/review";
    }
}
