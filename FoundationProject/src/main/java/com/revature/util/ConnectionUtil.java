package com.revature.util;

// We're goin to make a Singleton Connection instance

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    // To make this class a Singleton, We'll need the following things
    // private static instance
    private static Connection conn = null;

    // private constructor
    private ConnectionUtil() {

    }

    // public static Connection getInstance() method
    // this will create a new connection or allow us to reuse an existing connection
    public static Connection getConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        // MOST SECURE WAY
        // Use environment variables to store
        // Pull environment variables

        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
