package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Address;
import com.example.bookcrossing.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findBySurname(String surname);
}
