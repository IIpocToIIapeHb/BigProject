package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Prescription;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.webapphello.entity.PositionInfo.STATUS;
import static com.epam.webapphello.entity.PositionInfo.USER_ID;
import static com.epam.webapphello.entity.Prescription.*;

public class RecipeRowMapper implements RowMapper{
    @Override
    public Prescription map(ResultSet resultSet) throws SQLException {
        return new Prescription(resultSet.getLong(ID),
                        resultSet.getLong(USER_ID),
                        resultSet.getLong(MEDICINE_ID),
                        resultSet.getDate(VALID_UNTIL),
                        resultSet.getString(STATUS),
                        resultSet.getInt(AMOUNT));
    }
}
