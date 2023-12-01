package com.mycompany.loginapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
// import javax.swing.table.TableColumn;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Customer extends JFrame {

    private JButton searchButton, viewCartButton, viewOrderHistoryButton, editProfileButton, logoutButton;
    private JTextField searchField;
    private DefaultTableModel model;
    private JTable table;
    private JButton addToCartButton;
    private JComboBox<String> quantityComboBox;
    private Map<String, String> typeCodeMapping;
    private Map<String, Integer> shoppingCart = new HashMap<>();

    public Customer() {
        setTitle("Customer Page");
        setSize(1200, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 初始化按钮和文本框
        searchButton = createStyledButton("Search Products");
        viewCartButton = createStyledButton("View Cart");
        viewOrderHistoryButton = createStyledButton("View Order History");
        editProfileButton = createStyledButton("Edit Profile");
        logoutButton = createStyledButton("Log Out");

        searchField = new JTextField(20);

        // 初始化购物车按钮和选择数量的组件
        addToCartButton = createStyledButton("Add to Cart");
        quantityComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

        // 初始化产品类型和Code的映射
        typeCodeMapping = new HashMap<>();
        typeCodeMapping.put("track", "R");
        typeCodeMapping.put("controller", "C");
        typeCodeMapping.put("locomotive", "L");
        typeCodeMapping.put("rolling stack", "S");
        typeCodeMapping.put("train set", "M");
        typeCodeMapping.put("train pack", "P");

        // 添加按钮监听器
        searchButton.addActionListener(e -> showProductsByType());
        viewCartButton.addActionListener(e -> showCart());
        viewOrderHistoryButton.addActionListener(e -> showOrderHistory());
        editProfileButton.addActionListener(e -> showEditProfileDialog());
        logoutButton.addActionListener(e -> showLogoutDialog());
        addToCartButton.addActionListener(e -> addToCart());

        // 顶部面板
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // 创建表格和模型
        model = new DefaultTableModel();
        model.addColumn("Brand");
        model.addColumn("Name");
        model.addColumn("Code");
        model.addColumn("Price");

        table = new JTable(model);

        // 创建滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);

        // 创建右侧面板
        JPanel rightPanel = new JPanel(new GridLayout(7, 1));
        rightPanel.add(createLabelAndButtonPanel("To view your cart, click here:", viewCartButton));
        rightPanel.add(createLabelAndButtonPanel("To view your order history, click here:", viewOrderHistoryButton));
        rightPanel.add(createLabelAndButtonPanel("To edit your profile, click here:", editProfileButton));
        rightPanel.add(createLabelAndButtonPanel("To log out, click here:", logoutButton));

        // 布局设置
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // 底部添加购物车的组件
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(createLabelAndButtonPanel("Quantity:", quantityComboBox));
        bottomPanel.add(addToCartButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 创建带有标签和按钮的面板
    private JPanel createLabelAndButtonPanel(String labelText, JComponent component) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(label);
        panel.add(component);

        return panel;
    }

    // 创建样式化按钮
    private JButton createStyledButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(200, 24));
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        return button;
    }

    private void showProductsByType() {
        String productType = searchField.getText();

        if (typeCodeMapping.containsKey(productType.toLowerCase())) {
            String productCode = typeCodeMapping.get(productType.toLowerCase());
            showProducts(productCode);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid product type. Please enter a valid product type and try again.");
        }
    }

    private void showProducts(String productType) {
        // 查询数据库并将结果显示在表格中
        model.setRowCount(0); // 清空表格

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT brand, name, code, price FROM products WHERE code LIKE ? AND LENGTH(code) = 6";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, productType + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String brand = resultSet.getString("brand");
                    String name = resultSet.getString("name");
                    String code = resultSet.getString("code");
                    double price = resultSet.getDouble("price");

                    Object[] rowData = {brand, name, code, price};
                    model.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addToCart() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            String brand = (String) table.getValueAt(selectedRow, 0);
            String name = (String) table.getValueAt(selectedRow, 1);
            String code = (String) table.getValueAt(selectedRow, 2);
            double price = (double) table.getValueAt(selectedRow, 3);
            int quantity = Integer.parseInt((String) quantityComboBox.getSelectedItem());

            // 将产品信息和数量添加到购物车中
            shoppingCart.put(code, quantity);

            // 提示用户产品已添加到购物车
            JOptionPane.showMessageDialog(this, "Product added to cart:\nBrand: " + brand +
                    "\nName: " + name + "\nCode: " + code + "\nQuantity: " + quantity +
                    "\nPrice: " + price + "\nTotal Price: " + (price * quantity));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product from the table.");
        }
    }

    private void showCart() {
        // 打开购物车界面，并传递购物车数据
        Cart cartPage = new Cart(shoppingCart);
        cartPage.setVisible(true);
        dispose(); // 关闭当前界面
    }

    private void showOrderHistory() {
        JOptionPane.showMessageDialog(this, "Viewing Order History...");
    }

    private void showEditProfileDialog() {
        JOptionPane.showMessageDialog(this, "Showing Edit Profile Dialog...");
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
        SwingUtilities.invokeLater(() -> new Customer());
    }
}
