package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.*;
import com.example.bookcrossing.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/")
    public String Main( Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "/book/book-main";
    }

    @GetMapping("/book")
    public String bookMain( Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "/book/book-main";
    }

    @GetMapping("/book/add")
    public String bookAddMain(Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        Iterable<Status> statuses = statusRepository.findAll();
        model.addAttribute("statuses", statuses);

        Iterable<Language> languages = languageRepository.findAll();
        model.addAttribute("languages", languages);

        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        Iterable<Address> addresses = addressRepository.findAll();
        model.addAttribute("addresses", addresses);

        Iterable<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "/book/book-add";
    }

    @PostMapping("/book/add")
    public String bookAdd(    @RequestParam String title,
                              @RequestParam String genre,
                              @RequestParam String status,
                              @RequestParam String language,
                              @RequestParam String category,
                              @RequestParam String author,
                              @RequestParam String address,
                              @RequestParam String review,
                              Model model)
    {
        System.out.println(title);

        Genre genre1 = genreRepository.findByTitle(genre);
        System.out.println(genre1.getId());
        Status status1 = statusRepository.findByTitle(status);
        System.out.println(status1.getId());
        Language language1 = languageRepository.findByTitle(language);
        System.out.println(language1.getId());
        Category category1 = categoryRepository.findByTitle(category);
        System.out.println(category1.getId());
        Author author1 = authorRepository.findBySurname(author);
        System.out.println(author1.getId());
        Address address1 = addressRepository.findByStreet(address);
        System.out.println(address1.getId());
        Review review1 = reviewRepository.findByTitle(review);
        System.out.println(review1.getId());

        Book book1 = new Book(title,
                genre1,
                status1,
                language1,
                category1,
                address1,
                review1,
                author1);
        bookRepository.save(book1);

        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);
        if(!bookRepository.existsById(id))
        {
            return "redirect:/book";
        }
        return "/book/book-details";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable("id")long id,
                           Model model)
    {
        if(!bookRepository.existsById(id)){
            return "redirect:/book";
        }
        Optional<Book> post = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("book",res);

        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        Iterable<Status> statuses = statusRepository.findAll();
        model.addAttribute("statuses", statuses);

        Iterable<Language> languages = languageRepository.findAll();
        model.addAttribute("languages", languages);

        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        Iterable<Address> addresses = addressRepository.findAll();
        model.addAttribute("addresses", addresses);
        return "/book/book-edit";
    }
    @PostMapping("/book/{id}/edit")
    public String bookUpdate(@PathVariable("id")long id,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam String genre,
                             @RequestParam String status,
                             @RequestParam String language,
                             @RequestParam String category,
                             @RequestParam String address,
                             Model model)
    {
        Book book = bookRepository.findById(id).orElseThrow();

        Genre genre1 = genreRepository.findByTitle(genre);
        System.out.println(genre1.getId());
        Status status1 = statusRepository.findByTitle(status);
        System.out.println(status1.getId());
        Language language1 = languageRepository.findByTitle(language);
        System.out.println(language1.getId());
        Category category1 = categoryRepository.findByTitle(category);
        System.out.println(category1.getId());
        Author author1 = authorRepository.findBySurname(author);
        System.out.println(author1.getId());
        Address address1 = addressRepository.findByStreet(address);
        System.out.println(address1.getId());

        book.setTitle(title);
        book.setAuthor(author1);
        book.setGenre(genre1);
        book.setAddress(address1);
        book.setCategory(category1);
        book.setStatus(status1);
        book.setLanguage(language1);

        bookRepository.save(book);
        return "redirect:/book";
    }

    @PostMapping("/book/{id}/remove")
    public String bookDelete(@PathVariable("id") long id, Model model){
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
        return "redirect:/book";
    }

    @GetMapping("/book/filter")
    public String bookFilter(Model model){return "/book/book-filter";}

    @PostMapping("/book/filter/result")
    public String bookResult(@RequestParam String title, Model model)
    {
        List<Book> result = bookRepository.findByTitleContains(title);
        model.addAttribute("result", result);

        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "/book/book-filter";
    }
}
