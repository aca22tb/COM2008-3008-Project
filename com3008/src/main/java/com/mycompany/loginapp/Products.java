package com.mycompany.loginapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Products {

    private DefaultTableModel model;
    private JTable table;

    public Products() {
        // Set up the JFrame
        JFrame frame = new JFrame("Product Table Example");
        frame.setSize(1000, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the table model with column names
        model = new DefaultTableModel();
        model.addColumn("Brands' Name");
        model.addColumn("Name");
        model.addColumn("Code");
        model.addColumn("Price");

        // Create the JTable with the model
        table = new JTable(model);

        // Set the preferred width for the second column
        TableColumn column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(350);

        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Add some sample data to the table
        addRowToTable("Hornby", "L&MR No.58 'Tiger'", "P00312", 229.99);
        addRowToTable("Hornby", "Red Rover Train Set", "M00123", 119.99);
        addRowToTable("Hornby", "BR, Class 50, Co-Co 'Leviathan'", "L00654", 167.99);
        addRowToTable("Hornby", "Analogue Train and Accessory Controller", "C00879", 19.49);
        addRowToTable("Hornby", "Pullman third class brake no.162", "S00978", 53.49);

        addRowToTable("Bachmann", "Remote crossover turnout left, HO scale", "R00417", 122.00);
        addRowToTable("Bachmann", "Power Pack and Speed Controller", "C00258", 102.00);
        addRowToTable("Bachmann", "Alaska GP40", "L00582", 135.00);
        addRowToTable("Bachmann", "Boxcar-Union Pacific", "S00396", 39.99);
        addRowToTable("Bachmann", "Chattanooga Train Set (HO scale)", "M00159", 325.00);

        addRowToTable("Graham Farish", "Western Rambler Train Set", "M00915", 189.99);
        addRowToTable("Graham Farish", "Class 90/0 'Financial Times' BR Intercity", "L00375", 194.95);
        addRowToTable("Graham Farish", "7 Plank Wagon End Door BR GREY", "S00864", 19.95);
        addRowToTable("Graham Farish", "Gaugemaster Single Track Controller with Transformer", "C00624", 51.99);
        addRowToTable("Graham Farish", "LGB Right Electric Turnout, R1, 30°", "R00795", 67.49);

        addRowToTable("Peco", "00-9 Box Van Fyffes Bananas", "S00135", 22.90);
        addRowToTable("Peco", "00-9 Box Van Colman's Mustard", "S00513", 22.90);
        addRowToTable("Peco", "00-9 Box Van Bass", "S00531", 22.90);
        addRowToTable("Peco", "SL-600 Flexible Track, Wooden Sleeper", "R00264", 15.00);
        addRowToTable("Peco", "Bullhead OO Gauge Slip-Double", "R00624", 79.96);

        addRowToTable("Dapol", "Sentinel Y1/Y3 LNER 42", "L00642", 195.62);
        addRowToTable("Dapol", "Class 68 68014 Chiltern DCC & Sound", "L00831", 286.20);
        addRowToTable("Dapol", "LHT-626 Suburban B 4 Coach Set BR London #34 Lined Maroon", "M00318", 885.60);
        addRowToTable("Dapol", "LHT 633 No Division & Unnumbered GWR C & C Twin Cities", "M00729", 286.60);
        addRowToTable("Dapol", "2P-003-015 N Gauge B Set Coach Pack BR Crimson 66461 & 6464", "M00927", 37.80);
        // Add more sample data...

        // Make the frame visible
        frame.setVisible(true);
    }

    private void addRowToTable(String brand, String name, String code, double price) {
        Object[] rowData = {brand, name, code, price};
        model.addRow(rowData);

        // Insert into the database
        insertIntoDatabase(brand, name, code, price);
    }

    private void insertIntoDatabase(String brand, String name, String code, double price) {
        // Database connection parameters
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

        String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team056";
        String username = "team056";
        String password = "ohr4Kahbi";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO products (brand, name, code, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, brand);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, code);
                preparedStatement.setDouble(4, price);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Insert successful. Rows affected: " + rowsAffected);
            }   else {
                    System.out.println("Insert failed. No rows affected.");
            }   
        }
    } catch (SQLException e) {
        System.err.println("SQLException: " + e.getMessage());
        e.printStackTrace(); // 打印异常堆栈信息
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Products::new);
    }

    public void setVisible(boolean b) {
    }
}