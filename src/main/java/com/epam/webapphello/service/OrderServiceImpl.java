package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class OrderServiceImpl implements OrderService {

    private DaoHelperFactory daoHelperFactory;

    public OrderServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<Order> findOrderByStatusAndUser(final String status, final Long user_id) throws ServiceException {
        Optional<Order> order = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao orderDao = helper.createOrderDao();
            order = orderDao.findOrderByStatusAndUser(status,user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public void save(Order order, Long medicine_id, Integer required_amount) throws ServiceException {

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();

            Dao orderDaoSave = helper.createOrderSimpleDao();
            orderDaoSave.save(order);

            Optional<Order> orderWithId = null;
            OrderDao orderDaoFind = helper.createOrderDao();
            orderWithId = orderDaoFind.findOrderByStatusAndUser("not_paid",order.getUser_id());


            OrderMedicine orderMedicine = new OrderMedicine(medicine_id, required_amount,orderWithId.get().getId());
            Dao orderMedicineDao = helper.createOrderMedicineSimpleDao();
            orderMedicineDao.save(orderMedicine);
            helper.endTransaction();
        } catch (DAOException e) {

            throw new ServiceException(e);
        }
    }
}



