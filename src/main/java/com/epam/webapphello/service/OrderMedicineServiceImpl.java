package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderMedicineServiceImpl implements OrderMedicineService {

    private DaoHelperFactory daoHelperFactory;

    public OrderMedicineServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public void save(OrderMedicine orderMedicine) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            Dao orderMedicineDao = helper.createOrderMedicineSimpleDao();
            orderMedicineDao.save(orderMedicine);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<OrderMedicine> findOrderMedicine(Long orderId, Long medicineId)  throws ServiceException {
        Optional<OrderMedicine> orderMedicine = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicine = orderMedicineDao.findOrderMedicineByUserAndMedicine(orderId, medicineId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderMedicine;
    }



    @Override
    public void addMedicineOrderAmount(Long orderMedicineId, Integer oldMedicineAmount,Integer addMedicineAmount)  throws ServiceException {
        Integer medicineNumber = oldMedicineAmount+addMedicineAmount;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.changeMedicineOrderAmount(orderMedicineId, medicineNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeMedicine(Long orderMedicineId, Long recipeId, String recipeStatus) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            Dao orderMedicineDao = helper.createOrderMedicineSimpleDao();
            orderMedicineDao.removeById(orderMedicineId);

            if (recipeStatus.equals("pending approval")){
                Dao recipeDao = helper.createRecipeSimpleDao();
                recipeDao.removeById(recipeId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

