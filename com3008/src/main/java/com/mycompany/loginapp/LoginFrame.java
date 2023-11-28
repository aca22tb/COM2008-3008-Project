package com.mycompany.loginapp;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JLabel jLabel1, jLabel2;
    private JTextField jTextField1;
    private JPasswordField jPasswordField1;
    private JButton jButton1, jButton2;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        jLabel1 = new JLabel("Email:");
        jLabel2 = new JLabel("Password:");

        jTextField1 = new JTextField(20);
        jPasswordField1 = new JPasswordField(20);

        jButton1 = new JButton("Login");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jButton2 = new JButton("Sign Up");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jPasswordField1, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(150, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

  private void loginButtonActionPerformed(ActionEvent evt) {
    String username = jTextField1.getText();
    String password = new String(jPasswordField1.getPassword());

    try {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                openCustomerPage(username, role);  // 传递用户名和角色信息
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Invalid username or password.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private void openCustomerPage(String username, String role) {
    Customer customerPage = new Customer(username, role);
    customerPage.setVisible(true);
    }

    private void signUpButtonActionPerformed(ActionEvent evt) {
        SignUpFrame signUpFrame = new SignUpFrame();
        signUpFrame.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}