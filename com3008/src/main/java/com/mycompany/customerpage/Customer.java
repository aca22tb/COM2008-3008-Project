package com.mycompany.customerpage;

import javax.swing.*;

import com.mycompany.loginapp.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.productpage.*;
import com.mycompany.orderpage.*;

public class Customer extends JFrame {
    // Buttons and text field for various actions on the customer interface.
    private JButton orderButton, productButton, profileButton, searchButton, logoutButton;
    private JTextField searchField;

    // Constructor for initializing the customer interface.
    public Customer() {
        // Set the title, size, and default close operation for the JFrame.
        setTitle("User Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize buttons, text field, and set their properties.
        orderButton = new JButton("Order Page");
        productButton = new JButton("Product Page");
        profileButton = new JButton("Profile Page");
        searchButton = new JButton("Search");
        logoutButton = new JButton("Log Out");

        searchField = new JTextField(20);

        // Set preferred sizes for some buttons.
        orderButton.setPreferredSize(new Dimension(50, 40));
        productButton.setPreferredSize(new Dimension(150, 40));

        orderButton.addActionListener(e -> {
            // Open the order page
            new Order().setVisible(true);
            dispose(); // Close the current customer page if needed
        });

        productButton.addActionListener(e -> {
            // Open the product page
            new Products().setVisible(true);
            dispose(); // Close the current customer page if needed
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for a search term and show a message.
                String searchTerm = JOptionPane.showInputDialog(Customer.this, "Enter product name to search:");
                JOptionPane.showMessageDialog(Customer.this, "Searching for: " + searchTerm);
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the user profile dialog.
                showProfileDialog();}
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user for confirmation before logging out.
                Object[] options = {"Yes", "No"};
                int option = JOptionPane.showOptionDialog(
                        Customer.this,
                        "Are you sure you want to log out?",
                        "Log Out",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        
                if (option == JOptionPane.YES_OPTION) {
                    // Show a message, dispose of the current frame, and open the login frame.
                    JOptionPane.showMessageDialog(Customer.this, "Logging out...");
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });

        // Create panels for layout and add components to the JFrame.
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

        // Make the JFrame visible.
        setVisible(true);
    }

    // Action performed when the "Profile Page" button is clicked.
    private void showProfileDialog() {
        // Create a dialog to display the profile information
        JDialog profileDialog = new JDialog(this, "Profile Page", true);
        profileDialog.setSize(400, 300);

        /// Add components like labels and buttons to the profile dialog.
        JLabel nameLabel = new JLabel("Name: John Doe");  // Replace with actual user information
        JLabel emailLabel = new JLabel("Email: john.doe@example.com");  // Replace with actual user information

        JButton editButton = new JButton("Edit Profile");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new dialog for editing the profile
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

    // Action performed when the "Edit Profile" button is clicked.
    private void showEditProfileDialog() {
        // Create a modal dialog for editing the user profile.
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
    
        // Retrieve current user information, replace with actual data retrieval logic
        String currentFirstName = "CurrentFirstName";
        String currentLastName = "CurrentLastName";
        String currentEmail = "CurrentEmail";
        String currentPassword = "CurrentPassword";
        String currentPhone = "CurrentPhone";
        String currentAddress = "CurrentAddress";
        String currentBankDetails = "CurrentBankDetails";
    
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
    
            // Update the user information, replace with actual update logic)
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
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Customer());
    }
}