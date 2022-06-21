package com.epam.webapphello.connection;

import com.epam.webapphello.exception.ConnectionPoolException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {


    String url;
    String user;
    String pass;


    public ConnectionFactory() throws ConnectionPoolException {
        try {
            //   Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Driver is not found", e);
        }

        Properties properties = new Properties();
        try (InputStream in = ConnectionFactory.class.getResourceAsStream("/database.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new ConnectionPoolException("Could not load database.properties",e);
        }
        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        pass = properties.getProperty("db.password");
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Failed to connect with database", e);
        }
        return connection;
    }
}

