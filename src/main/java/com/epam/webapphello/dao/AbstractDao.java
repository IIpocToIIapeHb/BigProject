package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Identifable;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.RowMapper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends Identifable> implements Dao<T> {

    private final Connection connection;

    public AbstractDao(Connection connection){
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DAOException {
        try ( PreparedStatement statement = createStatement (query, params);
              ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while(resultSet.next()){
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
           throw new DAOException(e);
        }
    }
    protected PreparedStatement createStatement (String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length ; i++) {
            statement.setObject(i, params[i-1]);
        }
        return statement;
    }

    public List<T> getAll() throws DAOException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("select * from " + table, mapper);
    }


    protected Optional<T> executeForSingleResult(String query, RowMapper<T> mapper, Object... params) throws DAOException {
            List<T> items = executeQuery(query,mapper, params);
            if (items.size() == 1){
                return Optional.of(items.get(0));
            } else if(items.size() > 1){
                throw new IllegalArgumentException("More then one record found");
        } else {
                return Optional.empty();
        }
    }

    protected abstract String getTableName();

}
