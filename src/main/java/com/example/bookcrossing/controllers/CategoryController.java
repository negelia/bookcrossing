package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.Category;
import com.example.bookcrossing.models.Genre;
import com.example.bookcrossing.repo.CategoryRepository;
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
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public String categoryMain( Model model){
        Iterable<Category> category = categoryRepository.findAll();
        model.addAttribute("category", category);
        return "/category/category-main";
    }

    @GetMapping("/category/add")
    public String Main(Category category, Model model){
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories",categories);
        return "/category/category-add";
    }

    @PostMapping("/category/add")
    public String addressAdd( @RequestParam String title,
                              Model model)
    {
        Category category = new Category(title);
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @GetMapping("/category/{id}/edit")
    public String categoryEdit(@PathVariable("id")long id,
                            Model model)
    {
        if(!categoryRepository.existsById(id)){
            return "redirect:/category";
        }
        Optional<Category> post = categoryRepository.findById(id);
        ArrayList<Category> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("category",res);
        return "/category/category-edit";
    }
    @PostMapping("/category/{id}/edit")
    public String categoryUpdate(@PathVariable("id")long id,
                              @RequestParam String title,
                              Model model)
    {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setTitle(title);
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @PostMapping("/category/{id}/remove")
    public String categoryDelete(@PathVariable("id") long id, Model model){
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
        return "redirect:/category";
    }

}
