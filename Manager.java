/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loginapp;

import javax.swing.*;
import com.mycompany.loginapp.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Assume you have a User class with a role field
class User {
    private String username;
    private String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Other getters and setters...
}

public class Manager extends JFrame {

    private JButton createRecordButton, viewEditButton, profileButton, viewOrderButton, logoutButton, promoteButton, demoteButton;

    public Manager() {
        setTitle("Manager Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createRecordButton = new JButton("Create product record");
        viewEditButton = new JButton("View and Edit product record");
        profileButton = new JButton("Profile Page");
        viewOrderButton = new JButton("View orders");
        logoutButton = new JButton("Log Out");
        promoteButton = new JButton("Promote to Staff");
        demoteButton = new JButton("Demote from Staff");

        createRecordButton.setPreferredSize(new Dimension(50, 40));
        viewEditButton.setPreferredSize(new Dimension(150, 40));

        createRecordButton.addActionListener(e -> JOptionPane.showMessageDialog(Manager.this, "Creating Product Record..."));
        viewEditButton.addActionListener(e -> JOptionPane.showMessageDialog(Manager.this, "View/Edit Product Record..."));
        viewOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(Manager.this, "Opening View Order..."));

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileDialog();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int option = JOptionPane.showOptionDialog(
                        Manager.this,
                        "Are you sure you want to log out?",
                        "Log Out",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(Manager.this, "Logging out...");
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });

        promoteButton.addActionListener(e -> promoteUser());
        demoteButton.addActionListener(e -> demoteUser());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(Box.createHorizontalStrut(0));
        topPanel.add(viewOrderButton);
        topPanel.add(profileButton);
        topPanel.add(promoteButton);
        topPanel.add(demoteButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel pagesPanel = new JPanel(new GridLayout(12, 1));
        JLabel orderLabel = new JLabel("To create product record, click here:");
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesPanel.add(orderLabel);
        pagesPanel.add(createRecordButton);
        pagesPanel.add(Box.createVerticalStrut(10));

        JLabel productLabel = new JLabel("To view and edit product record, click here:");
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesPanel.add(productLabel);
        pagesPanel.add(viewEditButton);

        add(pagesPanel, BorderLayout.CENTER);
        

        setVisible(true);
    }

    private void showProfileDialog() {
        // ... (unchanged)
    }

    private void promoteUser() {
        // Implement the logic to promote the user to staff.
        // You can show a dialog, retrieve user information, and perform the necessary actions.

        // For illustration purposes, let's assume you have a list of users
        // In a real application, you would retrieve user information from a database
        List<User> userList = getUserList(); // You need to implement this method

        // Show a dialog to select a user to promote
        String[] usernames = userList.stream().map(User::getUsername).toArray(String[]::new);
        String selectedUser = (String) JOptionPane.showInputDialog(
                Manager.this,
                "Select a user to promote:",
                "Promote User",
                JOptionPane.QUESTION_MESSAGE,
                null,
                usernames,
                usernames[0]);

        if (selectedUser != null) {
            // Find the selected user in the list
            User userToPromote = userList.stream()
                    .filter(user -> user.getUsername().equals(selectedUser))
                    .findFirst()
                    .orElse(null);

            if (userToPromote != null) {
                // Update the user's role to "Staff"
                userToPromote.setRole("Staff");

                JOptionPane.showMessageDialog(Manager.this, "User promoted to Staff: " + selectedUser);
            } else {
                JOptionPane.showMessageDialog(Manager.this, "User not found: " + selectedUser);
            }
        }
    }

    private void demoteUser() {
        // Implement the logic to demote the user from staff.
        // You can show a dialog, retrieve user information, and perform the necessary actions.

        // For illustration purposes, let's assume you have a list of users
        // In a real application, you would retrieve user information from a database
        List<User> userList = getUserList(); // You need to implement this method

        // Show a dialog to select a user to demote
        String[] usernames = userList.stream().map(User::getUsername).toArray(String[]::new);
        String selectedUser = (String) JOptionPane.showInputDialog(
                Manager.this,
                "Select a user to demote:",
                "Demote User",
                JOptionPane.QUESTION_MESSAGE,
                null,
                usernames,
                usernames[0]);

        if (selectedUser != null) {
            // Find the selected user in the list
            User userToDemote = userList.stream()
                    .filter(user -> user.getUsername().equals(selectedUser))
                    .findFirst()
                    .orElse(null);

            if (userToDemote != null) {
                // Update the user's role to "Regular" (or whatever the default role is)
                userToDemote.setRole("Regular");

                JOptionPane.showMessageDialog(Manager.this, "User demoted: " + selectedUser);
            } else {
                JOptionPane.showMessageDialog(Manager.this, "User not found: " + selectedUser);
            }
        }
    }

    private List<User> getUserList() {
        // This method should be implemented to retrieve a list of users from a data source (e.g., a database)
        // For simplicity, we'll create a sample list here
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", "Regular"));
        userList.add(new User("user2", "Regular"));
        userList.add(new User("user3", "Regular"));
        return userList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Manager());
    }
}
