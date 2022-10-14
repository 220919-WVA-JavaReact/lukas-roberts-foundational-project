package com.revature.util;

// We're goin to make a Singleton Connection instance

import java.io.FileNotFoundException;
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

//        String url = System.getenv("url");
//        String username = System.getenv("username");
//        String password = System.getenv("password");

        String url = "";
        String username = "";
        String password = "";
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("src/main/resources/application.properties"));
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e);
        }
    }

}
