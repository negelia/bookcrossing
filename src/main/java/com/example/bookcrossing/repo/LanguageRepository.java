package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.Genre;
import com.example.bookcrossing.models.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    Language findByTitle(String title);
}
