package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.webapphello.entity.PositionInfo.STATUS;
import static com.epam.webapphello.entity.PositionInfo.USER_ID;
import static com.epam.webapphello.entity.Recipe.*;

public class RecipeRowMapper implements RowMapper{
    @Override
    public Recipe map(ResultSet resultSet) throws SQLException {
        return new Recipe(resultSet.getLong(ID),
                        resultSet.getLong(USER_ID),
                        resultSet.getLong(MEDICINE_ID),
                        resultSet.getDate(VALID_UNTIL),
                        resultSet.getString(STATUS),
                        resultSet.getInt(AMOUNT));
    }
}
