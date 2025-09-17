package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;

public class TodoAppDAO {

    // Fetch all todos from the database
    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();

        String query = "SELECT * FROM todos ORDER BY created_at DESC";

        DatabaseConnection dbConnection = new DatabaseConnection();
        try (Connection conn = dbConnection.getDBConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet res = stmt.executeQuery()) {

            while (res.next()) {
                Todo todo = new Todo();

                todo.setId(res.getInt("id"));
                todo.setTitle(res.getString("title"));
                todo.setDescription(res.getString("description"));
                todo.setCreated_at(res.getTimestamp("created_at").toLocalDateTime());
                todo.setUpdated_at(res.getTimestamp("updated_at").toLocalDateTime());
                todo.setCompleted(res.getBoolean("completed"));

                todos.add(todo);
            }
        }

        return todos;
    }
}
