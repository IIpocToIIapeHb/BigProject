package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.exception.DAOException;

public interface  MedicineDao {

    Medicine getByMId(Long id) throws DAOException;
}
