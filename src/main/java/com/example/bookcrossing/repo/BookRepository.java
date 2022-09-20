package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    //List<Book> findByTitle(String title);
    List<Book> findByTitleContains(String title);

    Book findByTitle(String title);
}
