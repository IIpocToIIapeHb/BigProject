package com.epam.webapphello.dao;

import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface PositionInfoDao extends Dao<PositionInfo>{
    public List<PositionInfo> getPositionsByUserIdAndOrderStatus(final Long userId,final String orderStatus) throws DAOException;
}
