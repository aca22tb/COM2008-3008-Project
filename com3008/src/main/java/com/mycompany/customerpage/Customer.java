package com.mycompany.customerpage;

import javax.swing.*;

import com.mycompany.loginapp.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame {
    private JButton orderButton, productButton, profileButton, searchButton, logoutButton;

    public Customer() {
        setTitle("User Page");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        orderButton = new JButton("Order Page");
        productButton = new JButton("Product Page");
        profileButton = new JButton("Profile Page");
        searchButton = new JButton("Search");
        logoutButton = new JButton("Log Out");

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the order page (replace with actual logic)
                JOptionPane.showMessageDialog(Customer.this, "Opening Order Page");
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the product page (replace with actual logic)
                JOptionPane.showMessageDialog(Customer.this, "Opening Product Page");
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the profile page (replace with actual logic)
                JOptionPane.showMessageDialog(Customer.this, "Opening Profile Page");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform search (replace with actual search logic)
                String searchTerm = JOptionPane.showInputDialog(Customer.this, "Enter product name to search:");
                JOptionPane.showMessageDialog(Customer.this, "Searching for: " + searchTerm);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform logout (replace with actual logout logic)
                int option = JOptionPane.showConfirmDialog(Customer.this, "Are you sure you want to log out?");
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(Customer.this, "Logging out");
                    // Perform any additional logout actions here
                    dispose(); // Close the user page
                    // Open the login page or perform any other necessary actions
                    new LoginFrame().setVisible(true);
                }
            }
        });

        setLayout(new FlowLayout());
        add(orderButton);
        add(productButton);
        add(profileButton);
        add(searchButton);
        add(logoutButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Customer();
            }
        });
    }
}