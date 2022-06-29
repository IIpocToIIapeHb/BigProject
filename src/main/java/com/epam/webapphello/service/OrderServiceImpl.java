package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class OrderServiceImpl implements OrderService {

    private DaoHelperFactory daoHelperFactory;

    public OrderServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<Order> findOrderByStatusAndUser(final String status, final Long user_id) throws ServiceException {
        Optional<Order> order = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao orderDao = helper.createOrderDao();
            order = orderDao.findOrderByStatusAndUser(status,user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public void save(Order order, Long medicine_id, Integer required_amount, Long userId, boolean isPrescriptionRequired) throws ServiceException {

        DaoHelper helper = null;
        try {
            helper = daoHelperFactory.create();
            helper.startTransaction();

            OrderDao orderDaoSave = helper.createOrderDao();
            orderDaoSave.save(order);

            OrderDao orderDaoFind = helper.createOrderDao();
            Optional<Order> orderWithId = orderDaoFind.findOrderByStatusAndUser("not_paid",order.getUser_id());


            OrderMedicine orderMedicine = new OrderMedicine(medicine_id, required_amount,orderWithId.get().getId());
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.save(orderMedicine);

            createRecipeIfAbsent(helper,userId,isPrescriptionRequired,orderMedicine.getMedicine_id());
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

    private void createRecipeIfAbsent(DaoHelper helper,Long userId, boolean isPrescriptionRequired, Long medicineId) throws DAOException {

        RecipeDao recipeDao = helper.createRecipeDao();
        if (isPrescriptionRequired) {
            Optional<Recipe> recipe= recipeDao.findRecipeByUserAndMedicineAndUnwantedStatus(userId, medicineId,"used");
            if (!recipe.isPresent()) {
                recipeDao.saveEmptyRecipe(userId,medicineId);
            }
        }
    }
}



