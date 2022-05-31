package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper{
    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getDate("creation_date"),
                        resultSet.getString("status"));
    }
}
