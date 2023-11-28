package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpFrame extends JFrame {

    private JLabel passwordLabel, confirmPasswordLabel, emailLabel, firstNameLabel, lastNameLabel,
            phoneNumberLabel, addressLabel, staffKeyLabel, managerKeyLabel;
    private JTextField emailField, firstNameField, lastNameField, phoneNumberField, addressField,
            staffKeyField, managerKeyField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton signUpButton;

    public SignUpFrame() {
        setTitle("Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //userIDLabel = new JLabel("UserID:");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm Password:");
        
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        phoneNumberLabel = new JLabel("Phone Number:");
        addressLabel = new JLabel("Address:");
        staffKeyLabel = new JLabel("Staff Key(optional):");
        managerKeyLabel = new JLabel("Manager Key(optional):");

        //userIDField = new JTextField(20);
        emailField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        addressField = new JTextField(20);
        staffKeyField = new JTextField(20);
        managerKeyField = new JTextField(20);

        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);

        signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signUp();
            }
        });

        JButton returnLoginButton = new JButton("Return to Login");
        returnLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                returnToLogin();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                               
                                .addComponent(emailLabel)
                                .addComponent(passwordLabel)
                                .addComponent(confirmPasswordLabel)
                                
                                .addComponent(firstNameLabel)
                                .addComponent(lastNameLabel)
                                .addComponent(phoneNumberLabel)
                                .addComponent(addressLabel)
                                .addComponent(staffKeyLabel)
                                .addComponent(managerKeyLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                //.addComponent(userIDField)
                                .addComponent(emailField)
                                .addComponent(passwordField)
                                .addComponent(confirmPasswordField)
                                
                                .addComponent(firstNameField)
                                .addComponent(lastNameField)
                                .addComponent(phoneNumberField)
                                .addComponent(addressField)
                                .addComponent(staffKeyField)
                                .addComponent(managerKeyField)))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(returnLoginButton)
                        .addComponent(signUpButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
               //.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                       // .addComponent(userIDLabel)
                       // .addComponent(userIDField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(confirmPasswordField))
                
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(firstNameLabel)
                        .addComponent(firstNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lastNameLabel)
                        .addComponent(lastNameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phoneNumberLabel)
                        .addComponent(phoneNumberField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressLabel)
                        .addComponent(addressField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(staffKeyLabel)
                        .addComponent(staffKeyField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(managerKeyLabel)
                        .addComponent(managerKeyField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(returnLoginButton)
                        .addComponent(signUpButton))
        );

        pack();
    }

    private void signUp() {
        //String userID = userIDField.getText(); // Update variable name
         String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
       
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String staffKey = staffKeyField.getText();
        String managerKey = managerKeyField.getText();

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Password and Confirm Password do not match.");
            return;
        }

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO users (email, password, firstName, lastName, phoneNumber, livingAddress, staffKey, managerKey, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, lastName);
                preparedStatement.setString(5, phoneNumber);
                preparedStatement.setString(6, address);

                // 判断是否提供了 staffKey 和 managerKey
                if (staffKey != null && !staffKey.isEmpty()) {
                    preparedStatement.setString(7, staffKey);
                    preparedStatement.setString(8, null); // 设置 managerKey 为 null
                    preparedStatement.setString(9, "staff");
                } else if (managerKey != null && !managerKey.isEmpty()) {
                    preparedStatement.setString(7, null); // 设置 staffKey 为 null
                    preparedStatement.setString(8, managerKey);
                    preparedStatement.setString(9, "manager");
                } else {
                    preparedStatement.setString(7, null);
                    preparedStatement.setString(8, null);
                    preparedStatement.setString(9, "customer"); // 默认角色为 customer
                }

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Account created successfully!");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Account creation failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    }

    private void returnToLogin() {
        this.dispose();
        new LoginFrame().setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpFrame().setVisible(true);
            }
        });
    }
}