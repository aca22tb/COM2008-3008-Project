package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame {

    private JButton orderButton, productButton, profileButton, searchButton, logoutButton;
    private JTextField searchField;
    private JButton goToStaffButton, goToManagerButton;

    // Add fields to store the user's information
    private String username;
    private String userRole;

    public Customer(String username, String userRole) {
        // Set the user's information
        this.username = username;
        this.userRole = userRole;
        System.out.println("Username: " + username);
        System.out.println("UserRole: " + userRole);
        setTitle("User Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        orderButton = new JButton("Order Page");
        productButton = new JButton("Product Page");
        profileButton = new JButton("Profile Page");
        searchButton = new JButton("Search");
        logoutButton = new JButton("Log Out");

        searchField = new JTextField(20);

        // Always create these buttons
        goToStaffButton = new JButton("Go to Staff Page");
        goToManagerButton = new JButton("Go to Manager Page");

        orderButton.addActionListener(e -> {
            new Order().setVisible(true);
            dispose();
        });

        productButton.addActionListener(e -> {
            new Products().setVisible(true);
            dispose();
        });

        searchButton.addActionListener(e -> {
            String searchTerm = JOptionPane.showInputDialog(Customer.this, "Enter product name to search:");
            JOptionPane.showMessageDialog(Customer.this, "Searching for: " + searchTerm);
        });

        profileButton.addActionListener(e -> showProfileDialog());

        logoutButton.addActionListener(e -> showLogoutDialog());

        goToStaffButton.addActionListener(e -> showKeyInputDialog("Enter Staff Key", "520", new Staff()));

        goToManagerButton.addActionListener(e -> showKeyInputDialog("Enter Manager Key", "1314", new Manager()));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(profileButton);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        topPanel.add(searchPanel);
        topPanel.add(Box.createHorizontalGlue());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);
        bottomPanel.add(goToStaffButton);
        bottomPanel.add(goToManagerButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel pagesPanel = new JPanel(new GridLayout(12, 1));
        JLabel orderLabel = new JLabel("To check your order, click here:");
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesPanel.add(orderLabel);
        pagesPanel.add(orderButton);
        pagesPanel.add(Box.createVerticalStrut(10));

        JLabel productLabel = new JLabel("Check out all of our products!!!");
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesPanel.add(productLabel);
        pagesPanel.add(productButton);

        add(pagesPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void showProfileDialog() {
        // 省略，根据需要添加用户配置对话框的代码
    }

    private void showLogoutDialog() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Log Out",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Logging out...");
            dispose();
            new LoginFrame().setVisible(true);
        }
    }

    private void showKeyInputDialog(String prompt, String correctKey, JFrame destinationFrame) {
        String input = JOptionPane.showInputDialog(this, prompt);
        if (input != null) {  // Check if the user clicked OK and didn't cancel
            if (input.equals(correctKey)) {
                destinationFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Key");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operation canceled. No key entered.");
        }
    }

    public static void main(String[] args) {
        // For testing purposes
        String username = "testuser"; // Replace this with the actual username
        String userRole = "Staff"; // Replace this with the actual user role

        SwingUtilities.invokeLater(() -> new Customer(username, userRole));
    }
}
