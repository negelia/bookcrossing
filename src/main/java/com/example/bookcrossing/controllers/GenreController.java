package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.Address;
import com.example.bookcrossing.models.Genre;
import com.example.bookcrossing.models.Language;
import com.example.bookcrossing.repo.AddressRepository;
import com.example.bookcrossing.repo.GenreRepository;
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
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genre")
    public String genreMain( Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "/genre/genre-main";
    }

    @GetMapping("/genre/add")
    public String Main(Genre genre, Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres",genres);
        return "/genre/genre-add";
    }

    @PostMapping("/genre/add")
    public String addressAdd( @RequestParam String title,
                              Model model)
    {
        Genre genre = new Genre(title);
        genreRepository.save(genre);
        return "redirect:/genre";
    }

    @GetMapping("/genre/{id}/edit")
    public String genreEdit(@PathVariable("id")long id,
                               Model model)
    {
        if(!genreRepository.existsById(id)){
            return "redirect:/genre";
        }
        Optional<Genre> post = genreRepository.findById(id);
        ArrayList<Genre> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("genre",res);
        return "/genre/genre-edit";
    }
    @PostMapping("/genre/{id}/edit")
    public String genreUpdate(@PathVariable("id")long id,
                                 @RequestParam String title,
                                 Model model)
    {
        Genre genre = genreRepository.findById(id).orElseThrow();
        genre.setTitle(title);
        genreRepository.save(genre);
        return "redirect:/genre";
    }

    @PostMapping("/genre/{id}/remove")
    public String genreDelete(@PathVariable("id") long id, Model model){
        Genre genre = genreRepository.findById(id).orElseThrow();
        genreRepository.delete(genre);
        return "redirect:/genre";
    }
}
