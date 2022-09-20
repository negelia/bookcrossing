package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.Author;
import com.example.bookcrossing.models.Status;
import com.example.bookcrossing.repo.AuthorRepository;
import com.example.bookcrossing.repo.BookRepository;
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
public class AuthorController {
    @Autowired
    public AuthorRepository authorRepository;

    @GetMapping("/author")
    public String authorMain( Model model){
        Iterable<Author> author = authorRepository.findAll();
        model.addAttribute("author", author);
        return "/author/author-main";
    }

    @GetMapping("/author/add")
    public String Main(Author author, Model model){
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors",authors);
        return "/author/author-add";
    }

    @PostMapping("/author/add")
    public String authorAdd(@RequestParam String surname,
                            @RequestParam String name,
                            @RequestParam String middle,
                            Model model)
    {
        Author author = new Author(surname, name, middle);
        authorRepository.save(author);
        return "redirect:/author";
    }
    @GetMapping("/author/{id}/edit")
    public String authorEdit(@PathVariable("id")long id,
                             Model model)
    {
        if(!authorRepository.existsById(id)){
            return "redirect:/author";
        }
        Optional<Author> post = authorRepository.findById(id);
        ArrayList<Author> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("author",res);
        return "/author/author-edit";
    }
    @PostMapping("/author/{id}/edit")
    public String authorUpdate(@PathVariable("id")long id,
                               @RequestParam String surname,
                               @RequestParam String name,
                               @RequestParam String middle,
                               Model model)
    {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setSurname(surname);
        author.setName(name);
        author.setMiddle(middle);
        authorRepository.save(author);
        return "redirect:/author";
    }

    @PostMapping("/author/{id}/remove")
    public String authorDelete(@PathVariable("id") long id, Model model){
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.delete(author);
        return "redirect:/author";
    }
}
