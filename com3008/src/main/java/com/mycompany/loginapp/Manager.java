package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JFrame {

    private JButton promoteUserButton, demoteStaffButton, viewOrdersButton, viewOrderHistoryButton, profileButton, logoutButton, goToCustomerPageButton;

    public Manager() {
        setTitle("Manager Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 初始化按钮
        promoteUserButton = createStyledButton("Promote User to Staff");
        demoteStaffButton = createStyledButton("Demote Staff");
        viewOrdersButton = createStyledButton("View Orders");
        viewOrderHistoryButton = createStyledButton("View Order History");
        profileButton = createStyledButton("Profile Page");
        logoutButton = createStyledButton("Log Out");
        goToCustomerPageButton = createStyledButton("Customer Page");

        // 添加按钮监听器
        promoteUserButton.addActionListener(e -> promoteUserToStaff());
        demoteStaffButton.addActionListener(e -> demoteStaff());
        viewOrdersButton.addActionListener(e -> viewOrders());
        viewOrderHistoryButton.addActionListener(e -> viewOrderHistory());
        goToCustomerPageButton.addActionListener(e -> openCustomerPage());
        profileButton.addActionListener(e -> showProfileDialog());
        logoutButton.addActionListener(e -> showLogoutDialog());

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProfileDialog();}
        });

        // 顶部面板
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.add(viewOrdersButton);
        topPanel.add(profileButton);
        topPanel.add(goToCustomerPageButton);

        // 底部面板
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);

        // 中间面板
        JPanel pagesPanel = new JPanel(new GridLayout(6, 1));
        pagesPanel.add(createLabelAndButtonPanel("Promote a User to Staff", promoteUserButton));
        pagesPanel.add(createLabelAndButtonPanel("Demote a Staff Member", demoteStaffButton));
        pagesPanel.add(createLabelAndButtonPanel("View Current Orders", viewOrdersButton));
        pagesPanel.add(createLabelAndButtonPanel("View Order History", viewOrderHistoryButton));
        pagesPanel.add(createLabelAndButtonPanel("Customer Page", goToCustomerPageButton));
        pagesPanel.add(createLabelAndButtonPanel("Profile Page", profileButton));

        // 布局设置
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(pagesPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

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

    // 创建带有标签和按钮的面板
    private JPanel createLabelAndButtonPanel(String labelText, JButton button) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);
        panel.add(button);

        return panel;
    }

    // 创建样式化按钮
    private JButton createStyledButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(200, 24));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        return button;
    }

    private void promoteUserToStaff() {
        String email = JOptionPane.showInputDialog(this, "Enter the email of the user to promote:");

        if (email != null && !email.isEmpty()) {
            // 查询用户并更新角色为 Staff
            if (updateUserRole(email, "Staff", "520")) {
                JOptionPane.showMessageDialog(this, "User promoted to staff successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "User not found or could not be promoted.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email. Please enter a valid email.");
        }
    }

    private void demoteStaff() {
        String email = JOptionPane.showInputDialog(this, "Enter the email of the staff member to demote:");

        if (email != null && !email.isEmpty()) {
            // 查询用户并更新角色为 customer
            if (updateUserRole(email, "customer", null)) {
                JOptionPane.showMessageDialog(this, "Staff member demoted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Staff member not found or could not be demoted.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email. Please enter a valid email.");
        }
    }

   private boolean updateUserRole(String email, String role, String staffKey) {
    try {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE users SET role = ?, staffKey = ? WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role);
            preparedStatement.setString(2, staffKey);
            preparedStatement.setString(3, email);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User role and staffKey updated successfully.");
            } else {
                System.out.println("No rows affected. User not found or update failed.");
            }

            return rowsAffected > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    private void viewOrders() {
        JOptionPane.showMessageDialog(this, "Viewing orders...");
    }

    private void viewOrderHistory() {
        JOptionPane.showMessageDialog(this, "Viewing order history...");
    }

    private void openCustomerPage() {
        Customer customerPage = new Customer();
        customerPage.setVisible(true);
        dispose();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Manager());
    }
}
