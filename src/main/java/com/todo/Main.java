package com.todo;

import com.todo.util.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
// Import TodoAppGUI if it's in another package
import com.todo.gui.TodoAppGUI; // Change the package as needed

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to establish database connection: " + e.getMessage());
            System.exit(1);
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to set look and feel: " + ex.getMessage());
        }

        try {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    new TodoAppGUI().setVisible(true);
                } catch (Exception ex) {
                    System.err.println("Error starting the application: " + ex.getMessage());
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            System.err.println("Error initializing GUI: " + ex.getMessage());
        }
    }
}