package com.epam.webapphello.service;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;

public interface OrderMedicineService {
     void save(OrderMedicine item) throws ServiceException;
}
