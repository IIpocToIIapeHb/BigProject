package com.epam.webapphello.mapper;



import com.epam.webapphello.entity.MedicineCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineCategoryRowMapper implements RowMapper{
    @Override
    public MedicineCategory map(ResultSet resultSet) throws SQLException {
        return new  MedicineCategory(resultSet.getLong("id"),
                        resultSet.getString("name"));
    }
}
