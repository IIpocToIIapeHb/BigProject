package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Identifable;
import com.epam.webapphello.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper{
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("role"),
                        resultSet.getDouble("amount"));
                      //  resultSet.getByte("is_blocked")==0 ? false:true);
    }
}
