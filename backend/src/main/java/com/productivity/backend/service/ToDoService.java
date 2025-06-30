package com.productivity.backend.service;


import com.productivity.backend.model.TodoItem;
import com.productivity.backend.model.User;
import com.productivity.backend.repository.TodoRepository;
import com.productivity.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TodoItem> getTodoForUser(String username) {
        User user = userRepository.findByUsername(username);
        return todoRepository.findByUserId(user);
    }

    @Transactional
    public void createTodoItem(TodoItem todo, String username) {
        try {
            User user = userRepository.findByUsername(username);
            todo.setCompleted(false);
            todo.setDueDate(todo.getDueDate() != null ? todo.getDueDate() : LocalDateTime.now().plusDays(1));
            todo.setUser(user);
            TodoItem saved = todoRepository.save(todo);
            user.getTodoItems().add(saved);
            userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the Todo item!");
        }
    }

    public void deleteTodoItem(String id) {
        todoRepository.deleteById(id);
    }

    public void updateTodoItem(TodoItem todo) {
        todoRepository.save(todo);
    }

    public Optional<TodoItem> getById(String id) {
        return todoRepository.findById(id);
    }
}
