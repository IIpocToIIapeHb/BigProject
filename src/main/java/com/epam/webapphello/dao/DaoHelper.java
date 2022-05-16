package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.connection.ProxyConnection;
import com.epam.webapphello.exception.ConnectionException;
import com.epam.webapphello.exception.DAOException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    public ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws DAOException {
        try {
            this.connection = pool.getConnection();
        } catch (ConnectionException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void close() throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public void startTransaction() throws DAOException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void endTransaction() throws DAOException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
