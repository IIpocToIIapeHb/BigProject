package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.*;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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

    public void dateValidation(DaoHelper helper, List<PositionInfo> positions) throws DAOException {
        Date currentDate = new Date(System.currentTimeMillis());
        for (PositionInfo position:positions) {
            if (position.getRecipeValidUntil()!=null && position.getRecipeValidUntil().before(currentDate)
                    && position.getRecipeStatus().equals("approved")){
                RecipeDao recipeDao = helper.createRecipeDao();
                recipeDao.changeStatus(position.getRecipeId(), "overdue");
            }
        }
    }
}
