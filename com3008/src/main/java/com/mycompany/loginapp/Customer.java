package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame {

    private JButton orderButton, productButton, profileButton, searchButton, logoutButton;
    private JTextField searchField;
    private JButton goToStaffButton, goToManagerButton;

    public Customer(String username, String userRole) {
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
        // Create a dialog to display the profile information
        JDialog profileDialog = new JDialog(this, "Profile Page", true);
        profileDialog.setSize(400, 300);

        // Add components to the profile dialog
        JLabel nameLabel = new JLabel("Name: John Doe");  // Replace with actual user information
        JLabel emailLabel = new JLabel("Email: john.doe@example.com");  // Replace with actual user information

        JButton editButton = new JButton("Edit Profile");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new dialog for editing the profile (you need to implement this)
                showEditProfileDialog();
            }
        });

        // Add components to the profile dialog's content pane
        JPanel profilePanel = new JPanel(new GridLayout(10, 1));
        profilePanel.add(nameLabel);
        profilePanel.add(emailLabel);
        profilePanel.add(editButton);

        profileDialog.getContentPane().add(profilePanel);
        profileDialog.setLocationRelativeTo(this);
        profileDialog.setVisible(true);
    }

        private void showEditProfileDialog() {
            // Create a modal dialog
            JDialog editProfileDialog = new JDialog(this, "Edit Profile", true);
            editProfileDialog.setSize(400, 300);
            editProfileDialog.setLayout(new GridLayout(8, 2)); // Adjust the layout based on your needs
    
            // Components for user input
            JTextField firstNameField = new JTextField();
            JTextField lastNameField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField passwordField = new JPasswordField();
            JTextField phoneField = new JTextField();
            JTextField addressField = new JTextField();
            JTextField bankDetailsField = new JTextField();
            JButton saveButton = new JButton("Save");
    
            // Retrieve current user information (replace with actual data retrieval logic)
            String currentFirstName = "FirstName";
            String currentLastName = "LastName";
            String currentEmail = "Email";
            String currentPassword = "Password";
            String currentPhone = "Phone";
            String currentAddress = "Address";
            String currentBankDetails = "BankDetails";
    
            // Set default values to the input fields
            firstNameField.setText(currentFirstName);
            lastNameField.setText(currentLastName);
            emailField.setText(currentEmail);
            passwordField.setText(currentPassword);
            phoneField.setText(currentPhone);
            addressField.setText(currentAddress);
            bankDetailsField.setText(currentBankDetails);
    
            // Add components to the dialog
            editProfileDialog.add(new JLabel("First Name:"));
            editProfileDialog.add(firstNameField);
            editProfileDialog.add(new JLabel("Last Name:"));
            editProfileDialog.add(lastNameField);
            editProfileDialog.add(new JLabel("Email:"));
            editProfileDialog.add(emailField);
            editProfileDialog.add(new JLabel("Password:"));
            editProfileDialog.add(passwordField);
            editProfileDialog.add(new JLabel("Phone:"));
            editProfileDialog.add(phoneField);
            editProfileDialog.add(new JLabel("Address:"));
            editProfileDialog.add(addressField);
            editProfileDialog.add(new JLabel("Bank Details:"));
            editProfileDialog.add(bankDetailsField);
            editProfileDialog.add(new JLabel()); // Empty label for spacing
            editProfileDialog.add(saveButton);
    
            // Add action listener to the save button
            saveButton.addActionListener(e -> {
                // Retrieve values from input fields
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                String newEmail = emailField.getText();
                String newPassword = passwordField.getText();
                String newPhone = phoneField.getText();
                String newAddress = addressField.getText();
                String newBankDetails = bankDetailsField.getText();
    
                // Update the user information (replace with actual update logic)
                // For simplicity, let's just print the updated information
                System.out.println("Updated First Name: " + newFirstName);
                System.out.println("Updated Last Name: " + newLastName);
                System.out.println("Updated Email: " + newEmail);
                System.out.println("Updated Password: " + newPassword);
                System.out.println("Updated Phone: " + newPhone);
                System.out.println("Updated Address: " + newAddress);
                System.out.println("Updated Bank Details: " + newBankDetails);
    
                // Close the dialog
                editProfileDialog.dispose();
            });
    
            // Set the dialog to be visible
            editProfileDialog.setVisible(true);
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