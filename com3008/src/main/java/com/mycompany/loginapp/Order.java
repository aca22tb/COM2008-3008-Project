package com.mycompany.loginapp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Order extends JFrame implements ActionListener {
    // GUI components and data structures
    private JLabel label2, label4;
    private JTextField textField2, textField4;
    private JButton addButton, viewButton, deleteButton, clearButton, exitButton;
    private JPanel productPanel;
    private ArrayList<String[]> products = new ArrayList<String[]>();

    // Constructor to set up the initial frame and components
    public Order() {
        // Set frame properties
        setTitle("Product Management and Shopping Cart");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize and configure GUI components
        label2 = new JLabel("Product Name: ");
        label2.setBorder(new EmptyBorder(0, 20, 0, 0));

        label4 = new JLabel("Quantity: ");
        label4.setBorder(new EmptyBorder(0, 20, 0, 0));

        textField2 = new JTextField(45);
        textField4 = new JTextField(5);

        addButton = new JButton("Add");
        viewButton = new JButton("View");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        addButton.addActionListener(this);
        viewButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Set up the productPanel with GridLayout
        productPanel = new JPanel(new GridLayout(10, 2));

        JPanel label2TextField2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label2TextField2Panel.add(label2);
        label2TextField2Panel.add(textField2);

        JPanel label4TextField4Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label4TextField4Panel.add(label4);
        label4TextField4Panel.add(textField4);

        productPanel.add(label2TextField2Panel);
        productPanel.add(label4TextField4Panel);
        productPanel.add(addButton);
        productPanel.add(viewButton);
        productPanel.add(deleteButton);
        productPanel.add(clearButton);
        productPanel.add(exitButton);

        setLayout(new GridLayout(1, 2));
        add(productPanel);

        // Make the frame visible
        setVisible(true);
    }

    // ActionListener method to handle button clicks
    public void actionPerformed(ActionEvent e) {
        // Add button click
        if (e.getSource() == addButton) {
            // Add a product to the list and show a success message
            String[] product = new String[4];
            product[1] = textField2.getText();
            product[3] = textField4.getText();
            products.add(product);
            JOptionPane.showMessageDialog(this, "Product added successfully");
            clearProductFields();
        } 
        // View button click
        else if (e.getSource() == viewButton) {
            // Display a table of products
            showProducts();
        } 
        // Delete button click
        else if (e.getSource() == deleteButton) {
            // Delete a product based on user input
            deleteProduct();
        } 
        // Clear button click
        else if (e.getSource() == clearButton) {
            // Clear the text fields
            clearProductFields();
        } 
        // Exit button click
        else if (e.getSource() == exitButton) {
            // Exit the application
            System.exit(0);
        }
    }

    // Clear the text fields for product name and quantity
    private void clearProductFields() {
        textField2.setText("");
        textField4.setText("");
    }

    // Display a table of products in a new frame
    private void showProducts() {
        // Prepare data for the JTable
        String[] columns = {"Product Code", "Product Brand", "Product Name", "Quantity", "Price"};
        Object[][] data = new Object[products.size()][5];

        for (int i = 0; i < products.size(); i++) {
            data[i][0] = products.get(i)[0];
            data[i][1] = "";
            data[i][2] = products.get(i)[1];
            data[i][3] = products.get(i)[3];
            data[i][4] = products.get(i)[2];
        }

        // Create a JTable and display it in a new frame
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("View Products");
        frame.add(scrollPane);
        frame.setSize(900, 500);
        frame.setVisible(true);
    }

    // Delete a product based on user input
    private void deleteProduct() {
        // Prompt the user for the product name to delete
        String productNameToDelete = JOptionPane.showInputDialog(this, "Enter product name to delete:");

        // Iterate through the products to find and delete the specified product
        for (Iterator<String[]> iterator = products.iterator(); iterator.hasNext();) {
            String[] product = iterator.next();
            if (product[1].equals(productNameToDelete)) {
                iterator.remove();
                JOptionPane.showMessageDialog(this, "Product deleted successfully");
                showProducts(); // Refresh the view after deleting a product
                clearProductFields();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found");
    }

    public static void main(String[] args) {
        new Order();
    }
}