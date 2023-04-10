package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderInfo;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.OrderInfoRowMapper;
import com.epam.webapphello.mapper.OrderRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderInfoDaoImpl extends AbstractDao<OrderInfo> implements OrderInfoDao{


    private static final String GET_ALL_PAID_ORDERS =
            " SELECT Pharmacy.order.id, Pharmacy.order.user_id, Pharmacy.order.creation_date," +
                    " Pharmacy.order.status, Pharmacy.order.delivery_status," +
                    " user.name, user.surname, user.birth, user.address" +
                    " FROM Pharmacy.order" +
                    " JOIN user " +
                    " ON user.id=Pharmacy.order.user_id " +
                    " WHERE Pharmacy.order.status ='paid';";

    private static final String GET_ORDER_INFO_BY_ID =   " SELECT Pharmacy.order.id, Pharmacy.order.user_id, Pharmacy.order.creation_date," +
            " Pharmacy.order.status, Pharmacy.order.delivery_status," +
            " user.name, user.surname, user.birth, user.address" +
            " FROM Pharmacy.order" +
            " JOIN user " +
            " ON user.id=Pharmacy.order.user_id " +
            " WHERE Pharmacy.order.status ='paid' AND Pharmacy.order.id= ? ;";;

    public OrderInfoDaoImpl(Connection connection) {
        super(connection, new OrderInfoRowMapper());
    }


    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected Map<String, Object> getFields(OrderInfo item) {
        return null;
    }

    @Override
    public List<OrderInfo> getAllPaidOrders() throws DAOException {
        return executeQuery(GET_ALL_PAID_ORDERS);
    }

    @Override
    public Optional<OrderInfo> getOrderInfoById(long orderInfoId) throws DAOException {
        return executeForSingleResult(GET_ORDER_INFO_BY_ID,orderInfoId);
    }
}
