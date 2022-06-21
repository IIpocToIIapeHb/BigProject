package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.exception.DAOException;

public interface  MedicineDao {

    Medicine getByMedicineId(Long id) throws DAOException;
}
