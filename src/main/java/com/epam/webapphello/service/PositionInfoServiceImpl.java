package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

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
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return positions;
    }

    public Double calcTotalPrice(){
        Double totalPrice = 0.0;
        for (PositionInfo position:positions) {
            totalPrice+=position.getTotal();
        }
        return totalPrice;
    }
}
