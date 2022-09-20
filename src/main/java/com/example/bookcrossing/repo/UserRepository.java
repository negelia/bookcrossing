package com.example.bookcrossing.repo;

import com.example.bookcrossing.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
