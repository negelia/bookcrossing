package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.Genre;
import com.example.bookcrossing.models.Language;
import com.example.bookcrossing.models.Status;
import com.example.bookcrossing.repo.GenreRepository;
import com.example.bookcrossing.repo.LanguageRepository;
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
public class LanguageController {
    @Autowired
    private LanguageRepository languageRepository;

    @GetMapping("/language")
    public String languageMain( Model model){
        Iterable<Language> languages = languageRepository.findAll();
        model.addAttribute("languages", languages);
        return "/language/language-main";
    }

    @GetMapping("/language/add")
    public String Main(Genre language, Model model){
        Iterable<Language> languages = languageRepository.findAll();
        model.addAttribute("languages",languages);
        return "/language/language-add";
    }

    @PostMapping("/language/add")
    public String languageAdd( @RequestParam String title,
                              Model model)
    {
        Language language = new Language(title);
        languageRepository.save(language);
        return "redirect:/language";
    }

    @GetMapping("/language/{id}/edit")
    public String languageEdit(@PathVariable("id")long id,
                             Model model)
    {
        if(!languageRepository.existsById(id)){
            return "redirect:/language";
        }
        Optional<Language> post = languageRepository.findById(id);
        ArrayList<Language> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("language",res);
        return "/language/language-edit";
    }
    @PostMapping("/language/{id}/edit")
    public String languageUpdate(@PathVariable("id")long id,
                               @RequestParam String title,
                               Model model)
    {
        Language language = languageRepository.findById(id).orElseThrow();
        language.setTitle(title);
        languageRepository.save(language);
        return "redirect:/language";
    }

    @PostMapping("/language/{id}/remove")
    public String languageDelete(@PathVariable("id") long id, Model model){
        Language language = languageRepository.findById(id).orElseThrow();
        languageRepository.delete(language);
        return "redirect:/language";
    }

}
