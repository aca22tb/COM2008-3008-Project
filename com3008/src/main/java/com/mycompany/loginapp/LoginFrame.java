package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JLabel emailLabel, passwordLabel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                login();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openSignUpFrame();
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
                                .addComponent(passwordLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(emailField)
                                .addComponent(passwordField)))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addComponent(signUpButton))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(loginButton)
                        .addComponent(signUpButton))
        );

        pack();
    }

    private void login() {
    String email = emailField.getText();
    String password = new String(passwordField.getPassword());

    try {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                String staffKey = resultSet.getString("staffKey");

                // 检查角色
                if ("staff".equals(role)) {
                    // 如果是 staff，检查 staff key 是否符合条件
                    if (isValidStaffKey(staffKey)) {
                        openRolePage(role);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid staff key. Please try again.");
                    }
                } else {
                    // 其他角色直接打开对应界面
                    openRolePage(role);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private boolean isValidStaffKey(String staffKey) {
    // 在这里添加检查 staff key 是否有效的逻辑，例如和预设的值进行比较
    // 如果 staff key 有效，返回 true；否则返回 false。
    return "520".equals(staffKey);
}




  private void openRolePage(String role) {
    switch (role) {
        case "customer":
            new Customer();
            break;
        case "staff":
            new Staff();
            break;
        case "manager":
            new Manager();
            break;
        default:
            JOptionPane.showMessageDialog(this, "Invalid user role.");
    }
    dispose();
}




    private void openSignUpFrame() {
        new SignUpFrame().setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
