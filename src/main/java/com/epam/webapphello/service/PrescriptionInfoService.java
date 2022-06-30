package com.epam.webapphello.service;

import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface PrescriptionInfoService {

    List<PrescriptionInfo> getAllByStatus(String PrescriptionStatus) throws ServiceException;

}
