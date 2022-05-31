package com.epam.webapphello.service;

import com.epam.webapphello.dao.Dao;
import com.epam.webapphello.dao.DaoHelper;
import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.dao.UserDao;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderMedicineServiceImpl implements OrderMedicineService {

    private DaoHelperFactory daoHelperFactory;

    public OrderMedicineServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public void save(OrderMedicine orderMedicine) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            Dao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.save(orderMedicine);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

