package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ConnectionPool;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.service.UserServiceImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainUserMap {
    public static void main(String[] args) throws DAOException {
        User user = new User("Vasya", "Pupkin", "login","password","user",1000);
        MainUserMap mainUserMap = new MainUserMap();
        UserDaoImpl userDao= new UserDaoImpl(ConnectionPool.getInstance().getConnection());
        userDao.save(user);

    }

    public Map<String, Object> getFields(User item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(User.NAME, item.getName());
        fields.put(User.SURNAME, item.getSurname());
        fields.put(User.ROLE, item.getRole());
        fields.put(User.AMOUNT, item.getAmount());
        return fields;
    }

}
