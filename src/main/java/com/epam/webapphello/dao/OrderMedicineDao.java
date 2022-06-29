package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.exception.DAOException;

import java.util.Optional;

public interface OrderMedicineDao extends Dao<OrderMedicine> {
    Optional<OrderMedicine> findOrderMedicineByUserAndMedicine(Long userId, Long medicineId) throws DAOException;


    boolean changeMedicineOrderAmount(Long orderMedicineId, Integer medicineNumber) throws DAOException;
}
