package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.*;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;

public class PositionInfoServiceImpl implements PositionInfoService {

    private DaoHelperFactory daoHelperFactory;
    List<PositionInfo> positions = null;

    public PositionInfoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public List<PositionInfo> getPositions(Long userId, String orderStatus) throws ServiceException {
        positions = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PositionInfoDao PositionDao = helper.createPositionInfoDao();
            positions = PositionDao.getPositionsByUserIdAndOrderStatus(userId,orderStatus);

            dateValidation(helper,positions);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return positions;
    }

    public BigDecimal calcTotalPrice(){
        Double totalPrice = 0.0;
        for (PositionInfo position:positions) {
            totalPrice+=position.getTotal();
        }
        BigDecimal result = new BigDecimal(totalPrice);
        result  = result.setScale(2,RoundingMode.HALF_UP);
        return result;
    }

    @Override
    public boolean pay(List<PositionInfo> positions, User user, BigDecimal totalPrice) throws ServiceException,ServiceErrorException  {
        try (DaoHelper helper = daoHelperFactory.create()) {
            validation (helper,user,totalPrice,positions);

            MedicineDao medicineDao = helper.createMedicineDao();
            for (PositionInfo position : positions) {

                Medicine medicine=  medicineDao.getByMedicineId(position.getMedicineId());
                Integer medicineStockQuantity = medicine.getAmount();
                int newMedicineQuantity = medicineStockQuantity-position.getRequiredAmount();
                medicine.setAmount(newMedicineQuantity);
                medicineDao.save(medicine);


               if(position.getMedicineWithRecipe()==1){
                   PrescriptionDao recipeDao = helper.createPrescriptionDao();
                   Recipe recipe =(Recipe) recipeDao.getById(position.getRecipeId());
                   int newRecipeAmount = recipe.getAmount()-position.getRequiredAmount();
                   recipe.setAmount(newRecipeAmount);
                   if (recipe.getAmount()==0){
                       recipe.setStatus("used");
                   }

                 recipeDao.save(recipe);
               }

            }

            BigDecimal newUserCount = user.getAmount().subtract(totalPrice);
            user.setAmount(newUserCount);
            UserDao userDao = helper.createUserDao();
            userDao.save(user);


            OrderDao orderDao = helper.createOrderDao();
            Order order = orderDao.findOrderByStatusAndUser("not_paid",user.getId()).get();
            order.setStatus("paid");
            orderDao.save(order);



        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<PositionInfo> getPositions(Long orderId) throws ServiceException {
        positions = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PositionInfoDao PositionDao = helper.createPositionInfoDao();
            positions = PositionDao.getPositionsByOrderId(orderId);


        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return positions;
    }

    private void validation(DaoHelper helper,User user, BigDecimal totalPrice,List<PositionInfo> positions) throws ServiceErrorException, DAOException {
        MedicineDao medicineDao = helper.createMedicineDao();
        for (PositionInfo position : positions) {
            if (position.getMedicineWithRecipe() == 1 && position.getRequiredAmount()> position.getRecipeAmount() && position.getRecipeAmount()!=0) {
                throw new ServiceErrorException("You exceed limits. Please check your order");
            }
            if (position.getMedicineWithRecipe() == 1 && (position.getRecipeStatus()==null || !position.getRecipeStatus().equals("approved"))) {
                throw new ServiceErrorException("There are not approved medicines. Please check your order");
            }
            Medicine medicine=  medicineDao.getByMedicineId(position.getMedicineId());
            Integer medicineStockQuantity = medicine.getAmount();
            if (position.getRequiredAmount()>medicineStockQuantity){
                throw new ServiceErrorException("There is no medicine " +
                        position.getMedicineName() + "in the stock in the amount of " +
                        position.getRequiredAmount() + ". Available Quantity " + medicineStockQuantity );
            }
            if (user.getAmount().doubleValue()<totalPrice.doubleValue()){
                throw new ServiceErrorException("You don't have enough money(((((");
            }

        }
    }


    public void dateValidation(DaoHelper helper, List<PositionInfo> positions) throws DAOException {
        Date currentDate = new Date(System.currentTimeMillis());
        for (PositionInfo position:positions) {
            if (position.getRecipeValidUntil()!=null && position.getRecipeValidUntil().before(currentDate)
                    && position.getRecipeStatus().equals("approved")){
                PrescriptionDao recipeDao = helper.createPrescriptionDao();
                recipeDao.changeStatus(position.getRecipeId(), "overdue");
            }
        }
    }
}
