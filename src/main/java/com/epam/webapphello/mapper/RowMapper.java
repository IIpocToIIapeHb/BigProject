package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.Identifable;
import com.epam.webapphello.entity.User;
import java.sql.ResultSet;


public interface RowMapper <T extends Identifable> {
    T map(ResultSet resultset);

    static RowMapper<? extends Identifable> create(String table){
        switch (table){
            case User.TABLE:
                return new UserRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table " + table);
        }
    }
}
