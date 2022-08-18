package com.epam.webapphello.service;

import com.epam.webapphello.dao.DaoHelper;
import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.dao.MedicineDao;
import com.epam.webapphello.dao.UserDao;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;


import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> user = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = helper.createUserDao();
            user = userDao.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public List<User> findUserBySurname(String searchingUser) throws ServiceException {
        List<User> foundUsers = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = helper.createUserDao();
            foundUsers = userDao.findUserBySurname(searchingUser);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return foundUsers;
    }

    @Override
    public List<User> changeLockStatus(long userId) throws ServiceException {
        List<User> foundUsers = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = helper.createUserDao();
            User user = userDao.getById(userId);
            if (user.isBlocked()){
                user.setBlocked(false);
            } else {
                user.setBlocked(true);
            }
            userDao.save(user);
            foundUsers = userDao.findUserBySurname(user.getSurname());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return foundUsers;
    }


}
