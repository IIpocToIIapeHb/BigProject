package com.epam.webapphello.service;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderMedicineService {
     void save(OrderMedicine item, Byte medicineWithRecipe, Long userId) throws ServiceException;
     Optional<OrderMedicine> findOrderMedicine(Long orderId, Long medicine) throws ServiceException;
     void addMedicineOrderAmount(Long orderMedicineId, Integer oldMedicineOrderAmount, Integer addMedicineOrderAmount) throws ServiceException;
     void removeMedicine(Long orderMedicineId, Long recipeId, String recipeStatus) throws ServiceException;
}
