package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class MedicineServiceImpl implements MedicineService {

    private DaoHelperFactory daoHelperFactory;

    public MedicineServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }




    @Override
    public List<Medicine> getAll() throws ServiceException {
        List<Medicine> medicines = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            MedicineDao medicineDao = helper.createMedicineDao();
            medicines = medicineDao.getAll();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return medicines;
    }

    @Override
    public List<Medicine> findProductByName(String searchingProduct) throws ServiceException {
        List<Medicine> foundProducts = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            MedicineDao medicineDao = helper.createMedicineDao();
            foundProducts = medicineDao.findProductByName(searchingProduct);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return foundProducts;
    }
}
