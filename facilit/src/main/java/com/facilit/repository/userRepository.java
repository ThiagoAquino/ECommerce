package com.facilit.repository;

import com.facilit.model.User;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository <User, String> {
    User findByLogin(String login);
}
