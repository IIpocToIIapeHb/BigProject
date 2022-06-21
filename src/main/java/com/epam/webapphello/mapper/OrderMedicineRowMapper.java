package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMedicineRowMapper implements RowMapper{
    @Override
    public OrderMedicine map(ResultSet resultSet) throws SQLException {
        return new OrderMedicine(resultSet.getLong("id"),
                        resultSet.getLong("medicine_id"),
                        resultSet.getInt("required_amount"),
                        resultSet.getLong("order_id"));
    }
}
