package com.productivity.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "todos")
@Data
public class TodoItem {

    @Id
    private String id;

    @NonNull
    private String title;

    private String description;

    private LocalDateTime dueDate;

    private boolean completed;

    @DBRef
    @JsonBackReference
    private User user;


}
