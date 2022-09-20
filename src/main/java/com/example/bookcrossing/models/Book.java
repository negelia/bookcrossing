package com.example.bookcrossing.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Author author;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Review review;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Status status;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Language language;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Genre genre;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Category category;


    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(String title, Genre genre, Status status, Language language, Category category, Address address, Review review, Author author) {
        this.title = title;
        this.genre = genre;
        this.status = status;
        this.language = language;
        this.category = category;
        this.address = address;
        this.review = review;
        this.author = author;
    }
}
