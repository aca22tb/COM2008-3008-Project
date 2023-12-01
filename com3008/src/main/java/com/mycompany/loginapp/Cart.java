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
    private int orderCount = 0; // 新增一个订单计数器

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
            if (orderCount == 0) {
                // 第一个订单，显示银行信息对话框
                showBankDetailsDialog();
                orderCount++;
            } else {
                // 第二个订单，直接完成订单
                completeOrder();
            }
        }
    }

    // 将确认订单的逻辑提取为一个方法
   // ... （之前的代码保持不变）

// 新增方法，保存购物信息到数据库
private void saveOrderToDatabase(String userEmail, Map<String, Integer> shoppingCart) {
    try {
        Connection connection = DatabaseConnection.getConnection();
        String insertOrderQuery = "INSERT INTO orders (user_email, product_code, quantity, order_status) VALUES (?, ?, ?, ?)";
        String updateStockQuery = "UPDATE products SET stock = stock - ? WHERE code = ?";

        try (PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderQuery);
             PreparedStatement updateStockStatement = connection.prepareStatement(updateStockQuery)) {

            // 开始事务
            connection.setAutoCommit(false);

            for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
                String productCode = entry.getKey();
                int quantity = entry.getValue();

                // 插入订单
                insertOrderStatement.setString(1, userEmail);
                insertOrderStatement.setString(2, productCode);
                insertOrderStatement.setInt(3, quantity);
                insertOrderStatement.setString(4, "Pending"); // 订单状态可以根据实际情况设定
                insertOrderStatement.executeUpdate();

                // 更新库存
                updateStockStatement.setInt(1, quantity);
                updateStockStatement.setString(2, productCode);
                updateStockStatement.executeUpdate();
            }

            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            // 回滚事务
            connection.rollback();
            e.printStackTrace();
        } finally {
            // 恢复自动提交
            connection.setAutoCommit(true);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// 将确认订单的逻辑修改为调用新的保存订单方法
private void completeOrder() {
    // 保存购物信息到数据库
    saveOrderToDatabase(userEmail, shoppingCart);

    // 清空购物车和表格
    shoppingCart.clear();
    model.setRowCount(0);

    JOptionPane.showMessageDialog(this, "Order confirmed!");

    // 在此处调用 showOrderHistory 方法显示购买历史记录
    showOrderHistory();
}

// ... （之后的代码保持不变）


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
            // 获取用户输入的银行信息
            String cardHolderName = cardHolderNameField.getText();
            String cardNumber = cardNumberField.getText();
            String cvv = cvvField.getText();
            String expiryDate = expiryDateField.getText();

            // 保存银行信息到数据库
            saveBankDetailsToDatabase(cardHolderName, cardNumber, cvv, expiryDate);

            // 显示订单确认信息
            JOptionPane.showMessageDialog(this, "Order confirmed! Bank details:\n" +
                    "Cardholder Name: " + cardHolderName + "\n" +
                    "Card Number: " + cardNumber + "\n" +
                    "CVV: " + cvv + "\n" +
                    "Expiry Date: " + expiryDate);
        }
    }

    // 保存银行信息到数据库
    private void saveBankDetailsToDatabase(String cardHolderName, String cardNumber, String cvv, String expiryDate) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO bank_details (user_email, card_holder_name, card_number, cvv, expiry_date) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // 这里假设 user_email 是对应用户的唯一标识，你可能需要根据实际情况修改
                // 例如，可以在用户登录时从数据库中获取 user_email，并将其存储在 Cart 类中
                String userEmail = "54321@qq.com"; // 替换为实际的用户邮箱

                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2, cardHolderName);
                preparedStatement.setString(3, cardNumber);
                preparedStatement.setString(4, cvv);
                preparedStatement.setString(5, expiryDate);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Bank details saved to database. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("Failed to save bank details to database. No rows affected.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 返回购物界面
   private void returnToShopping() {
        // Instantiate the Customer class
        //Customer customer = new Customer();

        // Show the Customer frame
        //customer.setVisible(true);

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


    // This method adds order details to the order_history table
    public void addOrderToHistory(String user_email, String product_code, int quantity) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO order_history (user_email, product_code, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user_email);
                pstmt.setString(2, product_code);
                pstmt.setInt(3, quantity);
                
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating order failed, no rows affected.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


