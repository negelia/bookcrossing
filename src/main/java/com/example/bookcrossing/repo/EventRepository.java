package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
    Event findByTitle(String title);
}
