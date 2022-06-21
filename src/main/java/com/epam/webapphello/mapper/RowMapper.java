package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface RowMapper <T extends Identifable> {
    T map(ResultSet resultset) throws SQLException;

    static RowMapper<? extends Identifable> create(String table){
        switch (table){
            case User.TABLE:
                return new UserRowMapper();
            case Medicine.TABLE:
                return new MedicineRowMapper();
            case Order.TABLE:
                return new OrderRowMapper();
            case Recipe.TABLE:
                return new RecipeRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table " + table);
        }
    }
}
