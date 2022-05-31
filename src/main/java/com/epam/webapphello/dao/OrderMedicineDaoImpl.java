package com.epam.webapphello.dao;

import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.OrderMedicineRowMapper;
import com.epam.webapphello.mapper.RowMapper;
import com.epam.webapphello.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderMedicineDaoImpl extends AbstractDao<OrderMedicine> implements OrderMedicineDao {


    public OrderMedicineDaoImpl(Connection connection) {
        super(connection, new OrderMedicineRowMapper());
    }

    @Override
    protected String getTableName() {
        return OrderMedicine.TABLE;
    }

    @Override
    protected Map<String, Object> getFields(OrderMedicine item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(OrderMedicine.MEDICINE_ID, item.getMedicine_id());
        fields.put(OrderMedicine.REQUIRED_AMOUNT, item.getRequired_amount());
        fields.put(OrderMedicine.ORDER_ID, item.getOrder_id());
        return fields;
    }

    @Override
    public Optional<OrderMedicine> getById(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DAOException {

    }
}


