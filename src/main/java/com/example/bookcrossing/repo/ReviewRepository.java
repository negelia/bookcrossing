package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Book;
import com.example.bookcrossing.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    Review findByTitle(String title);
}
