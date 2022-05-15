package com.epam.webapphello.connection;

import com.epam.webapphello.exception.ConnectionException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static ProxyConnection getConnection() throws SQLException, ConnectionException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ConnectionException("Driver is not found", e);
        }

        Properties properties = new Properties();

        try (InputStream in = ConnectionFactory.class.getResourceAsStream("/database.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new AssertionError("Could not load database.properties");
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String pass = properties.getProperty("db.password");

        Connection connection = DriverManager.getConnection(url, user, pass);

        return new ProxyConnection(connection, ConnectionPool.getInstance());
    }
}

