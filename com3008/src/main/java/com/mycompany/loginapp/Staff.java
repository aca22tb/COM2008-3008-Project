package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Staff extends JFrame {

    private JButton createRecordButton, viewEditButton, profileButton, viewOrderButton, logoutButton, goToCustomerPageButton;

    public Staff() {
        setTitle("Staff Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createRecordButton = new JButton("Create product record");
        viewEditButton = new JButton("View and Edit product record");
        profileButton = new JButton("Profile Page");
        viewOrderButton = new JButton("View orders");
        logoutButton = new JButton("Log Out");
        goToCustomerPageButton = createStyledButton("Customer Page");

        createRecordButton.setPreferredSize(new Dimension(50, 40));
        viewEditButton.setPreferredSize(new Dimension(150, 40));

        createRecordButton.addActionListener(e -> JOptionPane.showMessageDialog(Staff.this, "Creating Product Record..."));
        viewEditButton.addActionListener(e -> JOptionPane.showMessageDialog(Staff.this, "View/Edit Product Record..."));
        viewOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(Staff.this, "Opening View Order..."));
        goToCustomerPageButton.addActionListener(e -> openCustomerPage());

        /* 
        viewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(Staff.this, "Enter product name to search:");
                JOptionPane.showMessageDialog(Staff.this, "Searching for: " + searchTerm);
            }
        });
*/

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileDialog();}
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int option = JOptionPane.showOptionDialog(
                        Staff.this,
                        "Are you sure you want to log out?",
                        "Log Out",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(Staff.this, "Logging out...");
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(Box.createHorizontalStrut(0)); 
        topPanel.add(viewOrderButton);
        topPanel.add(profileButton);
        topPanel.add(goToCustomerPageButton);

        
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
        pagesPanel.add(createLabelAndButtonPanel("Customer Page", goToCustomerPageButton));

        
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

    private JButton createStyledButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(200, 24));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        return button;
    }
    private JPanel createLabelAndButtonPanel(String labelText, JButton button) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);
        panel.add(button);

        return panel;
    }
    private void openCustomerPage() {
            Customer customerPage = new Customer();
            customerPage.setVisible(true);
            dispose();
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Staff());
    }
    
}