package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.Order;
import com.epam.webapphello.exception.DAOException;

import java.util.List;

public interface  MedicineDao extends Dao<Medicine> {

    Medicine getByMedicineId(Long id) throws DAOException;
    List<Medicine> findProductByName(String searchingProduct)throws DAOException;

    Long findMedicineCategoryId(String medicineCategory) throws DAOException;
}
