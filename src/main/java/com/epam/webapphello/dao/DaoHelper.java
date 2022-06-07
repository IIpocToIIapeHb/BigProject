package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.connection.ProxyConnection;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.exception.DAOException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    public ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
            this.connection = pool.getConnection();
    }
    public ProxyConnection getConnection(){
        return connection;
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

    public Dao createMedicineDao() {
        return new MedicineDaoImpl(connection);
    }

    public OrderDao createOrderDao() {
        return new OrderDaoImpl(connection);
    }
    public Dao createOrderSimpleDao() {
        return new OrderDaoImpl(connection);
    }

    public Dao createOrderMedicineDao() {
        return new OrderMedicineDaoImpl(connection);
    }

    public RecipeDao createRecipeDao() {
        return new RecipeDaoImpl(connection);
    }

    public PositionInfoDao createPositionInfoDao() {
        return new PositionInfoDaoImpl(connection);
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
