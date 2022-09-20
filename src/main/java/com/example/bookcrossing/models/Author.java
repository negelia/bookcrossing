package com.example.bookcrossing.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String surname, name, middle;

    @OneToMany (mappedBy = "author", fetch = FetchType.EAGER)
    private Collection<Book> book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public Collection<Book> getBook() {
        return book;
    }

    public void setBook(Collection<Book> book) {
        this.book = book;
    }

    public Author(String surname, String name, String middle) {
        this.surname = surname;
        this.name = name;
        this.middle = middle;
    }
    public Author() {

    }
}
