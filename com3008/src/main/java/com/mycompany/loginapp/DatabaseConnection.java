package com.mycompany.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/team056";
    private static final String USERNAME = "team056";
    private static final String PASSWORD = "ohr4Kahbi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
    public static void testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            // Log the exception or throw a custom exception
            throw new RuntimeException("Failed to test the database connection.", e);
        }
    }
}
