package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Book;
import com.example.bookcrossing.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByTitle(String title);
}
