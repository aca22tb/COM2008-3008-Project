package com.mycompany.managerpage;

import javax.swing.*;
import com.mycompany.loginapp.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Creating a User class with a role field
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

}

public class Manager extends JFrame {

    //Buttons for various actions
    private JButton createRecordButton, viewEditButton, profileButton, viewOrderButton, logoutButton, promoteButton, demoteButton;

    //Set Frame properties
    public Manager() {
        setTitle("Manager Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Initialising buttons and setting their actions
        createRecordButton = new JButton("Create product record");
        viewEditButton = new JButton("View and Edit product record");
        profileButton = new JButton("Profile Page");
        viewOrderButton = new JButton("View orders");
        logoutButton = new JButton("Log Out");
        promoteButton = new JButton("Promote to Staff");
        demoteButton = new JButton("Demote from Staff");

        createRecordButton.setPreferredSize(new Dimension(50, 40));
        viewEditButton.setPreferredSize(new Dimension(150, 40));

        //Attaching action listeners to buttons for specific actions
        createRecordButton.addActionListener(e -> JOptionPane.showMessageDialog(Manager.this, "Creating Product Record..."));
        viewEditButton.addActionListener(e -> JOptionPane.showMessageDialog(Manager.this, "View/Edit Product Record..."));
        viewOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(Manager.this, "Opening View Order..."));

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Show profile dialogue
                showProfileDialog();}
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Prompt user for confirmation before logging out
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
                    //Show a message, dispose of the current frame and open the login frame
                    JOptionPane.showMessageDialog(Manager.this, "Logging out...");
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });
        //Buttons for the Promote and Demote function
        promoteButton.addActionListener(e -> promoteUser());
        demoteButton.addActionListener(e -> demoteUser());

        //Create panels for layout and add components to the JFrame
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

        //Set the JFrame visible
        setVisible(true);
    }

    //The action performed when the button "Profile Page" is clicked.
    private void showProfileDialog() {
        // Create a dialog to display the profile information
        JDialog profileDialog = new JDialog(this, "Profile Page", true);
        profileDialog.setSize(400, 300);

        // Add components to the profile dialog (labels, buttons)
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

    //Action performed to promote the user to staff
    private void promoteUser() { 
        //A list of already set users 
        List<User> userList = getUserList(); 

        //dialog to select a user to promote
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

    //Action performed to demote the user from staff
    private void demoteUser() {
        //Getting the List of users
        List<User> userList = getUserList(); 

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
                // Update the user's role to "Customer"
                userToDemote.setRole("Customer");

                JOptionPane.showMessageDialog(Manager.this, "User demoted to Customer: " + selectedUser);
            } else {
                JOptionPane.showMessageDialog(Manager.this, "User not found: " + selectedUser);
            }
        }
    }

    private List<User> getUserList() {
        // This method should be implemented to retrieve a list of users from the data base 
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", "Customer"));
        userList.add(new User("user2", "Customer"));
        userList.add(new User("user3", "Customer"));
        return userList;
    }

    // Action performed when the "Edit Profile" button is clicked.    
    private void showEditProfileDialog() {
        // Create a modal dialog for editing the user profile.
        JDialog editProfileDialog = new JDialog(this, "Edit Profile", true);
        editProfileDialog.setSize(400, 300);
        editProfileDialog.setLayout(new GridLayout(8, 2));

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

            // Update the user information ,replace with actual update logic
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
        SwingUtilities.invokeLater(() -> new Manager());
    }
}
