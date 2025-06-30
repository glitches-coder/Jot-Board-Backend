package com.productivity.backend.repository;

import com.productivity.backend.model.TodoItem;
import com.productivity.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<TodoItem, String> {
    List<TodoItem> findByUserId(User user);
}
