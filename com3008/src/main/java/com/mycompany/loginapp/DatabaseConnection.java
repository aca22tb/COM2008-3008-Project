package com.mycompany.loginapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/your_database";
    private static final String USERNAME = "team056";
    private static final String PASSWORD = "ohr4Kahbi";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
