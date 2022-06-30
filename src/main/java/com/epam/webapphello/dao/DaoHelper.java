package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.connection.ProxyConnection;
import com.epam.webapphello.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(DaoHelper.class);

    public ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
            this.connection = pool.getConnection();
    }
    public ProxyConnection getConnection(){
        return connection;
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public OrderDao createOrderDao() {
        return new OrderDaoImpl(connection);
    }

    public MedicineDao createMedicineDao() {
        return new MedicineDaoImpl(connection);
    }

    public OrderMedicineDao createOrderMedicineDao() {
        return new OrderMedicineDaoImpl(connection);
    }

    public RecipeDao createRecipeDao() {
        return new RecipeDaoImpl(connection);
    }

    public PositionInfoDao createPositionInfoDao() {
        return new PositionInfoDaoImpl(connection);
    }

    public PrescriptionInfoDao createPrescriptionInfoDao() {
        return new PrescriptionInfoDaoImpl(connection);
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

    public void rollback(){
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

}
