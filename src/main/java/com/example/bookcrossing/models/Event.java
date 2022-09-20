package com.example.bookcrossing.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, date;
    private Boolean proved;

    @OneToMany (mappedBy = "event")
    private Collection<User> users;

    @OneToMany (mappedBy = "event", fetch = FetchType.EAGER)
    private Collection<News> news;

    public Event() {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getProved() {
        return proved;
    }

    public void setProved(Boolean proved) {
        this.proved = proved;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<News> getNews() {
        return news;
    }

    public void setNews(Collection<News> news) {
        this.news = news;
    }

    public Event(String title, String date, Boolean proved) {
        this.title = title;
        this.date = date;
        this.proved = proved;
    }
}
