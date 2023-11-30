package com.mycompany.loginapp;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
        goToCustomerPageButton = createStyledButton("Go to Customer Page");

        // 添加按钮监听器
        promoteUserButton.addActionListener(e -> promoteUserToStaff());
        demoteStaffButton.addActionListener(e -> demoteStaff());
        viewOrdersButton.addActionListener(e -> viewOrders());
        viewOrderHistoryButton.addActionListener(e -> viewOrderHistory());
        goToCustomerPageButton.addActionListener(e -> openCustomerPage());
        profileButton.addActionListener(e -> showProfileDialog());
        logoutButton.addActionListener(e -> showLogoutDialog());

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
        pagesPanel.add(createLabelAndButtonPanel("Go to Customer Page", goToCustomerPageButton));
        pagesPanel.add(createLabelAndButtonPanel("Profile Page", profileButton));

        // 布局设置
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(pagesPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
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
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
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

    private void showProfileDialog() {
        // TODO: Add code for showing the profile dialog
        JOptionPane.showMessageDialog(this, "Showing Profile Dialog...");
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
