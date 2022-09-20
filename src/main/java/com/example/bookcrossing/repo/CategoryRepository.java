package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Category;
import com.example.bookcrossing.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByTitle(String title);
}
