package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
    News findByTitle(String title);
}

