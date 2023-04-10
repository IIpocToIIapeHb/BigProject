package com.epam.webapphello.service;

import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PositionInfoService {
    List<PositionInfo> getPositions(Long userId, String orderStatus) throws ServiceException;
    BigDecimal calcTotalPrice();
    boolean pay(List<PositionInfo> positions, User user,  BigDecimal totalPrice) throws ServiceException,ServiceErrorException;
    List<PositionInfo> getPositions(Long id) throws ServiceException;
}
