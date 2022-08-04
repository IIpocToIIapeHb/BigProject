package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.OrderInfo;
import static com.epam.webapphello.entity.OrderInfo.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;



public class OrderInfoRowMapper implements RowMapper{
    @Override
    public OrderInfo map(ResultSet resultSet) throws SQLException {
        return new OrderInfo(resultSet.getLong(ORDER_ID),
                        resultSet.getLong(USER_ID),
                        resultSet.getString(USER_NAME),
                        resultSet.getString(USER_SURNAME),
                        resultSet.getDate(USER_BIRTH),
                        resultSet.getString(USER_ADDRESS),
                        resultSet.getDate(CREATION_ORDER_DATE),
                        resultSet.getString(CLIENT_STATUS),
                        resultSet.getString(DELIVERY_STATUS));
    }
}
