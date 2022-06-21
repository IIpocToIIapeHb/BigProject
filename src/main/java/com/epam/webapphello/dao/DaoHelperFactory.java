package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.exception.DAOException;

public class DaoHelperFactory {

    public DaoHelper create() throws DAOException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
