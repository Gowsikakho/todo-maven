package com.todo;

import com.todo.util.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try {
            Connection cn=db_Connection.getDBConnection();
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to establish database connection: " + e.getMessage());
        }
    }
}