package com.example.bookcrossing.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToMany (mappedBy = "language", fetch = FetchType.EAGER)
    private Collection<Book> books;

    public Language() {

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

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public Language(String title) {
        this.title = title;
    }
}
