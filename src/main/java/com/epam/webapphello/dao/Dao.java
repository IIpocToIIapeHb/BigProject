package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Identifable;
import com.epam.webapphello.exception.DAOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifable> {

    Optional<T> getById(Long id) throws DAOException;

    List<T> getAll() throws DAOException;

    boolean save(T item) throws DAOException;

    void removeById(Long id) throws DAOException;

}
