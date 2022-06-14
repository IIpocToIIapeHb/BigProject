package com.epam.webapphello.dao;

import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.RowMapper;
import com.epam.webapphello.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = md5(?)";
    private static final String FIND_BY_ID = "select * from user where id = ? ";
    private static final String SHOW_USER_TABLE = "select * from ";
    private static final String DELETE_BY_ID = "delete from user where id = ?";
    private static final String SAVE_USER =
            "INSERT into user (id, name, surname, login, password, role, amount, is_blocked) \n" +
                    "VALUES (?,?,?,?,?,?,?,?);";

    public UserDaoImpl(Connection connection) {
        super(connection, new UserRowMapper());
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DAOException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD,
                login,
                password);
    }

    @Override
    public Optional<User> getById(Long id) throws DAOException {
        return executeForSingleResult(FIND_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public List<User> getAll() throws DAOException {
        String table = getTableName();
        //RowMapper<User> mapper = (RowMapper<User>) RowMapper.create(table);
        return executeQuery(SHOW_USER_TABLE);
    }

//    @Override
//    public void save(User item) throws DAOException {
//        try {
//            PreparedStatement statement = createStatement(SAVE_USER, item.getId(), item.getName(),
//                    item.getSurname(), item.getLogin(), item.getPassword(),
//                    item.getRole(), item.getAmount(), item.getIsBlocked());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//    }

    @Override
    protected Map<String, Object> getFields(User item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(User.ROLE, item.getRole());
        return fields;
    }


    @Override
    public boolean removeById(Long id) throws DAOException {
        try {
            PreparedStatement statement = createStatement(DELETE_BY_ID, id);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }
}


