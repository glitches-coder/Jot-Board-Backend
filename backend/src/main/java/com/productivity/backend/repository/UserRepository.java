package com.productivity.backend.repository;

import com.productivity.backend.model.User;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

}
