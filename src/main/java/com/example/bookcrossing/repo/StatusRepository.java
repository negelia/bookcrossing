package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Genre;
import com.example.bookcrossing.models.Status;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
    Status findByTitle(String title);
}
