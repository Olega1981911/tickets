package org.example.config;

import org.example.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.name";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    public ConnectionManager() {
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(PropertiesUtil.getProperty(URL_KEY),
                    PropertiesUtil.getProperty(USERNAME_KEY),
                    PropertiesUtil.getProperty(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
