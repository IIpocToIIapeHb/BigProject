package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
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
            for (Medicine medicine:foundProducts){
                long medicineCategoryId = medicine.getCategoryId();
                String medicineCategoryName = medicineDao.findMedicineCategoryName(medicineCategoryId);
                medicine.setCategoryName(medicineCategoryName);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return foundProducts;
    }

    @Override
    public void save(Medicine medicine, String medicineCategory) throws ServiceException {
        DaoHelper helper = null;
        try {
            helper = daoHelperFactory.create();
            helper.startTransaction();

            MedicineDao medicineDao = helper.createMedicineDao();
            Long medicineCategoryId = medicineDao.findMedicineCategoryId(medicineCategory);
            medicine.setCategoryId(medicineCategoryId);
            medicineDao.save(medicine);
            helper.endTransaction();
        } catch (DAOException e) {
            if (helper!=null){
                helper.rollback();
            }
            throw new ServiceException(e);
        } finally {
            if (helper!=null){
                helper.close();
            }
        }
    }
}
