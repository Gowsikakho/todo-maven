package com.todo.model;

import java.time.LocalDateTime;

public class Todo {
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // Default constructor
    public Todo() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
        this.completed = false;
    }

    // Constructor for creating a new Todo (without id)
    public Todo(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

    // Full constructor (usually used when reading from DB)
    public Todo(int id, String title, String description, boolean completed,
                LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public LocalDateTime getUpdated_at() { return updated_at; }
    public void setUpdated_at(LocalDateTime updated_at) { this.updated_at = updated_at; }

    // For easy debugging
    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
