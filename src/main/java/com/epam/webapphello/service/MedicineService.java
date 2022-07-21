package com.epam.webapphello.service;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MedicineService {
    List<Medicine> getAll() throws ServiceException;
    List<Medicine> findProductByName(String searchingProduct) throws ServiceException;

    void save(Medicine medicine, String medicineCategory)throws ServiceException;

}
