package com.mycompany.loginapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Cart extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JButton returnButton, confirmOrderButton, clearCartButton;

    public Cart(Map<String, Integer> shoppingCart) {
        setTitle("Shopping Cart");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 创建表格和模型
        model = new DefaultTableModel();
        model.addColumn("Product Name");
        model.addColumn("Quantity");
        model.addColumn("Unit Price");
        model.addColumn("Total Price");
        model.addColumn("Remove");

        table = new JTable(model);

        // 创建滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加按钮监听器
        returnButton = createStyledButton("Return");
        confirmOrderButton = createStyledButton("Confirm Order");
        clearCartButton = createStyledButton("Clear Cart");

        returnButton.addActionListener(e -> dispose()); // 关闭购物车界面，返回上一层
        confirmOrderButton.addActionListener(e -> confirmOrder());
        clearCartButton.addActionListener(e -> clearCart());

        // 布局设置
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // 底部添加按钮的组件
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(returnButton);
        bottomPanel.add(confirmOrderButton);
        bottomPanel.add(clearCartButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // 显示购物车内容
        displayShoppingCart(shoppingCart);

        setVisible(true);
    }

    // 创建样式化按钮
    private JButton createStyledButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        return button;
    }

    // 显示购物车内容
    private void displayShoppingCart(Map<String, Integer> shoppingCart) {
        // 查询数据库或使用其他途径获取产品信息，此处仅为示例
        for (Map.Entry<String, Integer> entry : shoppingCart.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            double unitPrice = getProductPrice(productName);
            double totalPrice = unitPrice * quantity;

            Object[] rowData = {productName, quantity, unitPrice, totalPrice, "Remove"};
            model.addRow(rowData);
        }
    }

    // 获取产品单价，此处为示例方法，实际项目中应该从数据库或其他途径获取
    private double getProductPrice(String productName) {
        // 假设产品单价如下
        switch (productName) {
            case "Product A":
                return 20.0;
            case "Product B":
                return 15.0;
            case "Product C":
                return 30.0;
            default:
                return 0.0;
        }
    }

    // 确认下单
    private void confirmOrder() {
        // TODO: Add code to confirm the order and update the order table in the database
        // 提示订单信息，此处为示例
        JOptionPane.showMessageDialog(this, "Order confirmed!");
    }

    // 清空购物车
    private void clearCart() {
        int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to clear the cart?",
                "Clear Cart",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            model.setRowCount(0); // 清空表格
            // TODO: Add code to clear the shopping cart in the database
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Cart(new java.util.HashMap<>()));
    }
}
