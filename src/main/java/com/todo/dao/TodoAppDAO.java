package com.todo.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;

public class TodoAppDAO {
    private static final String query = "SELECT * FROM todos ORDER BY created_at DESC";
    private static final String insertQuery = 
    "INSERT INTO todos (title, description, completed, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

    //Create a new todo in the database
    public int createTodo(Todo todo) throws SQLException {
        try(
            Connection conn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
        )
        {
            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.setTimestamp(4, Timestamp.valueOf(todo.getCreated_at())); // created_at
            stmt.setTimestamp(5, Timestamp.valueOf(todo.getUpdated_at())); // updated_at


            int affectedRows = stmt.executeUpdate();
            if(affectedRows==0){
                throw new SQLException("Creating todo failed, no rows affected.");
            }
            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    return generatedKeys.getInt(1);
                }
                else{
                    throw new SQLException("Creating todo failed, no ID obtained.");
                }
            }
        }
    }
   public void updateTodo(Todo todo) throws SQLException {
        String sql = "UPDATE todos SET title = ?, description = ?, completed = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setBoolean(3, todo.isCompleted());
            stmt.setInt(4, todo.getId());

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Updating todo failed, no rows affected (ID: " + todo.getId() + ")");
            }
        }
    }


    // Fetch all todos from the database
    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();

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
                // instead of using setters, we can use parameterized constructor
                // Todo todo = new Todo(res.getInt("id"), res.getString("title"),
                //res.getString("description"), res.getTimestamp("created_at").toLocalDateTime(),
                //res.getTimestamp("updated_at").toLocalDateTime(), res.getBoolean("completed"));

                todos.add(todo);
            }
        }

        return todos;
    }
}
