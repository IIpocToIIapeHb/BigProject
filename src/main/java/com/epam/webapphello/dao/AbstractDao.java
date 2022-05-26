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
import java.util.Map;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {


    private final Connection connection;
    private final RowMapper<T> mapper;

    public AbstractDao(Connection connection, RowMapper<T> mapper) {
        this.connection = connection;
        this.mapper = mapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws DAOException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected boolean executeUpdate(String query, Object... params) throws DAOException {
        try (PreparedStatement statement = createStatement(query, params)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    protected PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    public List<T> getAll() throws DAOException {
        String table = getTableName();
        // RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("select * from " + table);
    }


    protected Optional<T> executeForSingleResult(String query, Object... params) throws DAOException {
        List<T> items = executeQuery(query,params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More then one record found");
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getTableName();

//    @Override
//    public boolean save(T item) throws DAOException {
//        String query = null;
//        Map<String, Object> fields = getFields(item);
//        if (item.getId() == null) {
//            query = generateInsertQuery(fields);
//        } else {
//            query = generateUpdateQuery(fields) + item.getId();
//        }
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            return statement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//
//    }

    @Override
    public boolean save(T item) throws DAOException {
        boolean result;
        Map<String, Object> fields = getFields(item);
        Object[] params = generateMassiveValuesFromMap(fields);
        String query;
        if (item.getId() == null) {
            query = generateInsertQuery(fields);
        } else {
           query = generateUpdateQuery(fields)+item.getId();
        }
        result = executeUpdate(query,params);
        return result;
    }

    public Object[] generateMassiveValuesFromMap(Map<String, Object> fields){
        int i =0;
        Object[] massive = new Object[fields.size()];
        for (Map.Entry<String, Object> item : fields.entrySet()) {
            massive[i]=item.getValue();
            i++;
        }
        return massive;
    }

    public String generateInsertQuery(Map<String, Object> fields) {

        StringBuffer columns = new StringBuffer(" (");
        StringBuffer values = new StringBuffer(" (");

        for (Map.Entry<String, Object> item : fields.entrySet()) {
            columns.append(item.getKey() + ",");
            values.append("?,");
        }
        columns.deleteCharAt(columns.length() - 1);
        values.deleteCharAt(values.length() - 1);

        String result = "INSERT into " + getTableName() + columns +
                ") values" + values + ");";
        return result;
    }


    public String generateUpdateQuery(Map<String, Object> fields) {
        StringBuffer values = new StringBuffer();

        for (Map.Entry<String, Object> item : fields.entrySet()) {
            values.append(item.getKey() + "='" + item.getValue() + "',");
        }

        values.deleteCharAt(values.length() - 1);

        String result = "update " + getTableName() + " set "
                + values + " where id = ";

        return result;
    }

    protected abstract Map<String, Object> getFields(T item);

}
