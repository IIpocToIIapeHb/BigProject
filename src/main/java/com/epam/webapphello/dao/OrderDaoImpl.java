package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.OrderRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao{

    private static final String FIND_BY_STATUS = "select * from pharmacy.order where status = ? ";
    private static final String CHANGE_ORDER_DELIVERY_STATUS ="UPDATE pharmacy.order SET delivery_status = ? WHERE (id = ?);";

    public OrderDaoImpl(Connection connection) {
        super(connection, new OrderRowMapper());
    }

    @Override
    public Optional<Order> findOrderByStatusAndUser(final String status, final Long user_id) throws DAOException {
        return executeForSingleResult(FIND_BY_STATUS, status);
    }

    @Override
    public boolean changeOrderDeliveryStatus(String newDeliveryStatus, long orderId) throws DAOException {
        return executeUpdate(CHANGE_ORDER_DELIVERY_STATUS,
                newDeliveryStatus,
                orderId);
    }

    @Override
    protected String getTableName() {
        return Order.TABLE;
    }

    @Override
    protected Map<String, Object> getFields(Order item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Order.USER_ID, item.getUserId());
        fields.put(Order.CREATION_DATE, item.getDate());
        fields.put(Order.STATUS, item.getStatus());
        return fields;
    }





}
