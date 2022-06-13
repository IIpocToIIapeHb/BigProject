package com.epam.webapphello.service;

import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PositionInfoService {
    List<PositionInfo> getPositions(Long userId, String orderStatus) throws ServiceException;
    public BigDecimal calcTotalPrice();
}
