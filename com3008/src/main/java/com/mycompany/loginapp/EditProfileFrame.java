package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditProfileFrame extends JFrame {

    private JTextField firstNameField, lastNameField, phoneNumberField, livingAddressField;

    public EditProfileFrame(int userID, String role ,JFrame parentFrame) {
        setTitle("Edit Profile");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 获取用户信息
        String[] userInfo = getUserInfo(userID, role);

        // 初始化文本框
        firstNameField = new JTextField(userInfo[0], 20);
        lastNameField = new JTextField(userInfo[1], 20);
        phoneNumberField = new JTextField(userInfo[2], 10);
        livingAddressField = new JTextField(userInfo[3], 50);

        // JTextField firstNameField = new JTextField();
        // JTextField lastNameField = new JTextField();
        // JTextField phoneNumberField = new JTextField();
        // JTextField livingAddressField = new JTextField();


        // 创建标签和按钮
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel livingAddressLabel = new JLabel("Living Address:");

        JButton saveButton = new JButton("Save Changes");

        // 添加按钮监听器
        saveButton.addActionListener(e -> saveChanges(userID,role, parentFrame));

        // 创建面板和布局
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5);

        panel.add(firstNameLabel, constraints);
        constraints.gridy++;
        panel.add(lastNameLabel, constraints);
        constraints.gridy++;
        panel.add(phoneNumberLabel, constraints);
        constraints.gridy++;
        panel.add(livingAddressLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(firstNameField, constraints);
        constraints.gridy++;
        panel.add(lastNameField, constraints);
        constraints.gridy++;
        panel.add(phoneNumberField, constraints);
        constraints.gridy++;
        panel.add(livingAddressField, constraints);

        constraints.gridy++;
        panel.add(saveButton, constraints);

        // 添加面板
        add(panel);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        setVisible(true);
    }

    // 获取用户信息的方法
    private String[] getUserInfo(int userID, String role) {
        String[] userInfo = new String[4];

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query;

            if ("customer".equals(role)) {
                query = "SELECT firstName, lastName, phoneNumber, livingAddress FROM customers WHERE userID = ?";
            } else if ("staff".equals(role)) {
                query = "SELECT firstName, lastName, phoneNumber, livingAddress FROM staff WHERE userID = ?";
            } else if ("manager".equals(role)) {
                query = "SELECT firstName, lastName, phoneNumber, livingAddress FROM managers WHERE userID = ?";
            } else {
                throw new IllegalArgumentException("Invalid Role");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    userInfo[0] = resultSet.getString("firstName");
                    userInfo[1] = resultSet.getString("lastName");
                    userInfo[2] = resultSet.getString("phoneNumber");
                    userInfo[3] = resultSet.getString("livingAddress");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    // 保存更改的方法
    private void saveChanges(int userID, String role, JFrame parentFrame) {
        // 获取文本框中的值
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String livingAddress = livingAddressField.getText();

        // 更新用户信息到数据库
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query ;
            if ("customer".equals(role)) {
                query = "UPDATE customers SET firstName = ?, lastName = ?, phoneNumber = ?, livingAddress = ? WHERE userID = ?";
            } else if ("staff".equals(role)) {
                query = "UPDATE staff SET firstName = ?, lastName = ?, phoneNumber = ?, livingAddress = ? WHERE userID = ?";
            } else if ("manager".equals(role)) {
                query = "UPDATE managers SET firstName = ?, lastName = ?, phoneNumber = ?, livingAddress = ? WHERE userID = ?";
            } else {
                throw new IllegalArgumentException("Invalid role");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, phoneNumber);
                preparedStatement.setString(4, livingAddress);
                preparedStatement.setInt(5, userID);

                // preparedStatement.executeUpdate();
                int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Profile deatils saved to database. Rows affected: " + rowsAffected);
            } else {
                System.out.println("Failed to save Profile details to database. No rows affected.");
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 提示用户信息已保存
        JOptionPane.showMessageDialog(this, "Changes saved successfully!");

        // 关闭编辑个人资料界面
        parentFrame.dispose();

        // 刷新用户信息（可选）
        // 这里您可以选择刷新用户信息，例如更新 Customer 界面上显示的用户信息


        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Replace the following values with actual data or variables
            int userID = 123;  // Replace with the actual user ID
            String role = "customer";  // Replace with the actual role
            JFrame parentFrame = new JFrame();  // Replace with the actual parent frame
    
            new EditProfileFrame(userID, role, parentFrame);
        });
    }
    
    
}
