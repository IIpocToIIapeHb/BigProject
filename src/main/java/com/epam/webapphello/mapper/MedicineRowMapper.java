package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineRowMapper implements RowMapper{
    @Override
    public Medicine map(ResultSet resultSet) throws SQLException {
        return new  Medicine(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("dosage"),
                        resultSet.getBoolean("with_recipe"),
                        resultSet.getString("form"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("package_amount"),
                        resultSet.getDouble("price"),
                        resultSet.getString("path"),
                        resultSet.getLong("medicine_category_id"));
    }
}
