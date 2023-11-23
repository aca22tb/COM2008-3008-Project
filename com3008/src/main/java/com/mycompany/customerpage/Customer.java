package com.mycompany.customerpage;

import javax.swing.*;

import com.mycompany.loginapp.LoginFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame {
    private JButton orderButton, productButton, profileButton, searchButton, logoutButton;
    private JTextField searchField;

    public Customer() {
        setTitle("User Page");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        orderButton = new JButton("Order Page");
        productButton = new JButton("Product Page");
        profileButton = new JButton("Profile Page");
        searchButton = new JButton("Search");
        logoutButton = new JButton("Log Out");

        searchField = new JTextField(20);

        orderButton.setPreferredSize(new Dimension(50, 40));
        productButton.setPreferredSize(new Dimension(150, 40));

        orderButton.addActionListener(e -> JOptionPane.showMessageDialog(Customer.this, "Opening Order Page..."));
        productButton.addActionListener(e -> JOptionPane.showMessageDialog(Customer.this, "Opening Product Page..."));
        profileButton.addActionListener(e -> JOptionPane.showMessageDialog(Customer.this, "Opening Profile Page..."));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(Customer.this, "Enter product name to search:");
                JOptionPane.showMessageDialog(Customer.this, "Searching for: " + searchTerm);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int option = JOptionPane.showOptionDialog(
                        Customer.this,
                        "Are you sure you want to log out?",
                        "Log Out",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(Customer.this, "Logging out...");
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalGlue()); 
        topPanel.add(profileButton);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        topPanel.add(searchPanel);
        topPanel.add(Box.createHorizontalGlue()); 

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(logoutButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel pagesPanel = new JPanel(new GridLayout(12, 1));
        JLabel orderLabel = new JLabel("To check your order, click here:");
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesPanel.add(orderLabel);
        pagesPanel.add(orderButton);
        pagesPanel.add(Box.createVerticalStrut(10));
        
        JLabel productLabel = new JLabel("Check out all of our products!!!");
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesPanel.add(productLabel);
        pagesPanel.add(productButton);
        
        add(pagesPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Customer());
    }
}