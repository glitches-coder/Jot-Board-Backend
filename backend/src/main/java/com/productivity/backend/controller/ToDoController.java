package com.productivity.backend.controller;


import com.productivity.backend.model.TodoItem;
import com.productivity.backend.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/{username}")
    public ResponseEntity<List<TodoItem>> addTodoItem(Authentication auth) {
        List<TodoItem> todos = toDoService.getTodoForUser(auth.getName());
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/{username}")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem todo, Authentication auth, @PathVariable String username) {
        try {
            toDoService.createTodoItem(todo, username);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable String id, @RequestBody TodoItem todo, @PathVariable String username) {
        TodoItem existing = toDoService.getById(id).orElse(null);
        if(existing != null) {
            existing.setTitle(todo.getTitle());
            existing.setDescription(todo.getDescription());
            existing.setCompleted(todo.isCompleted());
            existing.setDueDate(todo.getDueDate());
            toDoService.updateTodoItem(existing);
            return new ResponseEntity<>(existing, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable String id) {
        Optional<TodoItem> existing = toDoService.getById(id);
        if(existing.isPresent()) {
            toDoService.deleteTodoItem(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

}
