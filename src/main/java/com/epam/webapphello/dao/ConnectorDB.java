package com.epam.webapphello.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException, IOException {

        FileReader reader = new FileReader("src\\main\\resources\\database.properties");

        Properties properties=new Properties();
        properties.load(reader);

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String pass = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, pass);
    }
}

