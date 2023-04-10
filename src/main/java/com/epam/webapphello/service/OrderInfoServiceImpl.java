package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.OrderInfo;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderInfoServiceImpl implements OrderInfoService {

    private DaoHelperFactory daoHelperFactory;

    public OrderInfoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<OrderInfo> getAllPaidOrders() throws ServiceException {
        List<OrderInfo> orders = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderInfoDao orderInfoDao = helper.createOrderInfoDao();
            orders = orderInfoDao.getAllPaidOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public Optional<OrderInfo> findOrderInfoById(long orderInfoId) throws ServiceException {
        Optional<OrderInfo> order = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderInfoDao orderInfoDao = helper.createOrderInfoDao();
            order = orderInfoDao.getOrderInfoById(orderInfoId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }
}

