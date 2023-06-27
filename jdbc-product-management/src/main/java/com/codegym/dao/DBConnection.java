package com.codegym.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/product_management";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "justin2002";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    JDBC_URL,
                    JDBC_USERNAME,
                    JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
