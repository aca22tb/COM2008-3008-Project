package com.mycompany.loginapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String userEmail; // 新增用户邮箱字段

    public Customer(String userEmail) {
        this.userEmail = userEmail; // 初始化用户邮箱

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

    Customer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 16));
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
        
    JFrame orderHistoryFrame = new JFrame("Order History");
    orderHistoryFrame.setSize(600, 400);
    orderHistoryFrame.setLayout(new BorderLayout());
    
    // Add components to display order history...
    // This is a placeholder for actual implementation

    orderHistoryFrame.setVisible(true);
    
        JOptionPane.showMessageDialog(this, "Viewing Order History...");
    }

    private void showEditProfileDialog() {
        // 查询数据库获取当前用户信息
        User currentUser = getCurrentUserByEmail(userEmail); // 使用 userEmail 获取用户信息

        if (currentUser != null) {
            // 创建一个新的对话框用于编辑和保存用户信息
            JDialog editProfileDialog = new JDialog(this, "Edit Profile", true);
            editProfileDialog.setSize(400, 300);
            editProfileDialog.setLayout(new BorderLayout());

            // 创建表格用于显示和编辑用户信息
            String[] columnNames = {"Attribute", "Value"};
            String[][] data = {
                    {"Email", currentUser.getEmail()},
                    {"Password", currentUser.getPassword()},
                    {"Phone Number", currentUser.getPhoneNumber()},
                    {"Address", currentUser.getLivingAddress()}
            };

            JTable userProfileTable = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(userProfileTable);

            // 创建保存按钮
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> saveUserProfile(userProfileTable, currentUser, editProfileDialog));

            // 将组件添加到对话框中
            editProfileDialog.add(scrollPane, BorderLayout.CENTER);
            editProfileDialog.add(saveButton, BorderLayout.SOUTH);

            // 显示对话框
            editProfileDialog.setVisible(true);
        } else {
            // 处理用户为 null 的情况，可能是由于数据库查询失败或其他原因
            JOptionPane.showMessageDialog(this, "Error: Unable to fetch user details. Please try again later.");
        }
    }

    private User getCurrentUserByEmail(String userEmail) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userEmail);
               
                 ResultSet resultSet = preparedStatement.executeQuery();


                if (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String livingAddress = resultSet.getString("livingAddress");

                    return new User(email, password, phoneNumber, livingAddress);
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveUserProfile(JTable userProfileTable, User currentUser, JDialog editProfileDialog) {
        // 获取用户编辑后的信息
        String newEmail = (String) userProfileTable.getValueAt(0, 1);
        String newPassword = (String) userProfileTable.getValueAt(1, 1);
        String newPhoneNumber = (String) userProfileTable.getValueAt(2, 1);
        String newAddress = (String) userProfileTable.getValueAt(3, 1);

        // TODO: 更新数据库中当前用户的信息
        updateCurrentUser(currentUser, newEmail, newPassword, newPhoneNumber, newAddress);

        // 关闭编辑对话框
        editProfileDialog.dispose();
    }

    private void updateCurrentUser(User currentUser, String newEmail, String newPassword,String newPhoneNumber, String newAddress) {
    try {
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE users SET email = ?, password = ?, phoneNumber = ?, livingAddress = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setString(3, newPhoneNumber);
            preparedStatement.setString(4, newAddress);
            preparedStatement.setString(5, currentUser.getEmail());

            System.out.println("Before update - User details: " + currentUser);
System.out.println("Executing SQL query: " + preparedStatement.toString()); // 输出 SQL 语句
            System.out.println("Parameters: " + newEmail + ", " + newPassword + ", " + newPhoneNumber + ", " + newAddress + ", " + currentUser.getEmail()); // 输出参数值

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User details updated successfully.");
                System.out.println("User details updated in the database. Rows affected: " + rowsAffected);
            } else {
                System.out.println("Failed to update user details in the database. No rows affected.");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
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
        SwingUtilities.invokeLater(() -> {
            // 替换为实际的用户邮箱地址
            String userEmail = "54321@qq.com";
            new Customer(userEmail);
        });

    }




// Additional method implementations will go here.
// For example, getBankDetailsForUser and updateCurrentUser methods as outlined before.


    // This method retrieves and displays the order history for the user
    public void viewOrderHistory() {
        String user_email = this.userEmail; // Assuming userEmail is an attribute of Customer class
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM order_history WHERE user_email = ? ORDER BY order_date DESC";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user_email);
                
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    // Extract order details from the result set
                    // Display them in the UI
                    // This is just a placeholder, actual implementation will need to update the UI components
                    System.out.println("Order ID: " + rs.getInt("order_id"));
                    System.out.println("Product Code: " + rs.getString("product_code"));
                    System.out.println("Quantity: " + rs.getInt("quantity"));
                    System.out.println("Order Date: " + rs.getTimestamp("order_date"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
