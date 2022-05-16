package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.exception.ConnectionException;
import com.epam.webapphello.exception.DAOException;

public class DaoHelperFactory {

    public DaoHelper create() throws DAOException {

        try {
            return new DaoHelper(ConnectionPool.getInstance());
        } catch (ConnectionException e) {
            throw new DAOException(e);
        }

    }
}
