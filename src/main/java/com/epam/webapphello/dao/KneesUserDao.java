package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.connection.ProxyConnection;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ConnectionException;
import com.epam.webapphello.exception.DAOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class KneesUserDao implements UserDao {
    public static void main(String[] args) throws DAOException {
        KneesUserDao simpleUserService = new KneesUserDao();
        System.out.println(simpleUserService.findUserByLoginAndPassword("BlackSwan", "Hello world"));

    }

    public Optional<User> findUserByLoginAndPassword(final String login, final String password) throws DAOException {

        ConnectionPool pool = ConnectionPool.getInstance();
        try (ProxyConnection connection = pool.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select id,name,login from user" +
                    " where login = ? and password = md5(?)")) {
                statement.setString(1, login);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new User(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("login")));
                    }
                }
            }
        } catch (SQLException | ConnectionException e) {
            throw new DAOException(e);
        }
        return Optional.empty();
    }


}
