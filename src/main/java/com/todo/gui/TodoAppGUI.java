package com.todo.gui;

import com.todo.dao.TodoAppDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TodoAppGUI extends JFrame {
    private final TodoAppDAO todoDAO;  // made final since it's only initialized once
    private JTable todoTable;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckBox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JComboBox<String> filterComboBox;

    public TodoAppGUI() {
        this.todoDAO = new TodoAppDAO();
        initializeComponents(); // call initialization in constructor
    }

    private void initializeComponents() {
        setTitle("Todo Application ✅"); // Added emoji just for a visible change
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Title", "Description", "Created At", "Completed", "Updated At"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Input fields
        titleField = new JTextField(20);
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        completedCheckBox = new JCheckBox("Completed");

        // Buttons
        addButton = new JButton("Add Todo");
        updateButton = new JButton("Update Todo");
        deleteButton = new JButton("Delete Todo");
        refreshButton = new JButton("Refresh Todo");

        // Filter dropdown
        String[] filterOptions = {"All", "Completed", "Pending"};
        filterComboBox = new JComboBox<>(filterOptions);

        // Layout (simple for now)
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(new JScrollPane(descriptionArea));
        inputPanel.add(completedCheckBox);
        inputPanel.add(filterComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(new JScrollPane(todoTable), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
