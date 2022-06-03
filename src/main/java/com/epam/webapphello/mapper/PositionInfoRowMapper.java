package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.PositionInfo;


import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.webapphello.entity.PositionInfo.*;

public class PositionInfoRowMapper implements RowMapper{
    @Override
    public PositionInfo map(ResultSet resultSet) throws SQLException {
        return new PositionInfo(resultSet.getLong(ORDER_MEDICINE_ID),
                        resultSet.getLong(USER_ID),
                        resultSet.getString(NAME),
                        resultSet.getString(SURNAME),
                        resultSet.getLong(ORDER_ID),
                        resultSet.getDate(CREATION_DATE),
                        resultSet.getString(STATUS),
                        resultSet.getLong(MEDICINE_ID),
                        resultSet.getString(MEDICINE_NAME),
                        resultSet.getByte(MEDICINE_WITH_RECIPE),
                        resultSet.getString(MEDICINE_PATH),
                        resultSet.getInt(REQUIRED_AMOUNT),
                        resultSet.getLong(RECIPE_ID),
                        resultSet.getString(RECIPE_STATUS),
                        resultSet.getDate(RECIPE_VALID_UNTIL),
                        resultSet.getDouble(MEDICINE_PRICE));

    }
}
