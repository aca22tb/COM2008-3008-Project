package com.mycompany.loginapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Cart extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JButton deleteButton, confirmOrderButton, returnButton;
    private Map<String, Integer> shoppingCart;

    public Cart(Map<String, Integer> shoppingCart) {
        setTitle("Shopping Cart");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.shoppingCart = shoppingCart;

        // 初始化表格模型
        model = new DefaultTableModel();
        model.addColumn("Brand");
        model.addColumn("Product");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total Price");

        // 初始化表格
        table = new JTable(model);

        // 创建滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);

        // 初始化按钮
        deleteButton = createStyledButton("Delete from Cart");
        confirmOrderButton = createStyledButton("Confirm Order");
        returnButton = createStyledButton("Return to Shopping");

        // 添加按钮监听器
        deleteButton.addActionListener(e -> deleteFromCart());
        confirmOrderButton.addActionListener(e -> confirmOrder());
        returnButton.addActionListener(e -> returnToShopping());

        // 创建底部面板
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(deleteButton);
        bottomPanel.add(confirmOrderButton);
        bottomPanel.add(returnButton);

        // 设置布局
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // 显示购物车内容
        displayShoppingCart();

        setVisible(true);
    }

    // 创建样式化按钮
    private JButton createStyledButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(150, 24));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        return button;
    }

    // 显示购物车内容
    private void displayShoppingCart() {
        // 清空表格
        model.setRowCount(0);

        for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
            String productCode = entry.getKey();
            int quantity = entry.getValue();

            try {
                Connection connection = DatabaseConnection.getConnection();
                String query = "SELECT brand, name, price FROM products WHERE code = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, productCode);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String brand = resultSet.getString("brand");
                        String name = resultSet.getString("name");
                        double price = resultSet.getDouble("price");

                        Object[] rowData = {brand, name, quantity, price, quantity * price};
                        model.addRow(rowData);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 从购物车中删除产品
    private void deleteFromCart() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            // 获取选定行的产品信息
            String product = (String) table.getValueAt(selectedRow, 1);

            // 从购物车中移除该产品
            shoppingCart.remove(product);

            // 更新表格显示
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product from the cart.");
        }
    }

    // 确认订单
    private void confirmOrder() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to confirm this order?", "Confirm Order", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // TODO: Add code to prompt the user for bank details and complete the order
            showBankDetailsDialog();
        }
    }

    // 弹出对话框以输入银行信息
    private void showBankDetailsDialog() {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JTextField cardHolderNameField = new JTextField();
        JTextField cardNumberField = new JTextField();
        JTextField cvvField = new JTextField();
        JTextField expiryDateField = new JTextField();

        panel.add(new JLabel("Cardholder Name:"));
        panel.add(cardHolderNameField);
        panel.add(new JLabel("Card Number:"));
        panel.add(cardNumberField);
        panel.add(new JLabel("CVV:"));
        panel.add(cvvField);
        panel.add(new JLabel("Expiry Date:"));
        panel.add(expiryDateField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Bank Details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // TODO: Add code to process the bank details and complete the order
            JOptionPane.showMessageDialog(this, "Order confirmed! Bank details:\n" +
                    "Cardholder Name: " + cardHolderNameField.getText() + "\n" +
                    "Card Number: " + cardNumberField.getText() + "\n" +
                    "CVV: " + cvvField.getText() + "\n" +
                    "Expiry Date: " + expiryDateField.getText());
        }
    }

    // 返回购物界面
    private void returnToShopping() {
        // Instantiate the Customer class
        Customer customer = new Customer();

        // Show the Customer frame
        customer.setVisible(true);

        // Close the current Cart frame
        dispose();
    }

    public static void main(String[] args) {
        // For testing purposes
        SwingUtilities.invokeLater(() -> {
            Map<String, Integer> testCart = Map.of("Product A", 2, "Product B", 1, "Product C", 3);
            new Cart(testCart);
        });
    }
}
