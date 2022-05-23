package com.epam.webapphello.connection;

import com.epam.webapphello.exception.ConnectionPoolException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public ConnectionFactory() throws ConnectionPoolException {
        try {
            //   Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Driver is not found", e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {

        Properties properties = new Properties();

        try (InputStream in = ConnectionFactory.class.getResourceAsStream("/database.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new ConnectionPoolException("Could not load database.properties",e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String pass = properties.getProperty("db.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Failed to connect with database", e);
        }
        return connection;
    }
}

