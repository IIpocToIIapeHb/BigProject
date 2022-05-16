package com.epam.webapphello.connection;

import com.epam.webapphello.exception.ConnectionException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public ConnectionFactory() throws ConnectionException {
        try {
            //   Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver is not found", e);
        }
    }

    public Connection getConnection() throws ConnectionException {


        Properties properties = new Properties();

        try (InputStream in = ConnectionFactory.class.getResourceAsStream("/database.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new ConnectionException("Could not load database.properties",e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String pass = properties.getProperty("db.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new ConnectionException("Failed to connect with database", e);
        }
        return connection;
    }
}

