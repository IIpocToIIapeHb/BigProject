package com.epam.webapphello.service;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.ServiceException;
import java.util.List;

public interface PrescriptionInfoService {
    List<PrescriptionInfo> getAllByStatus(String PrescriptionStatus) throws ServiceException;
    List<PrescriptionInfo> getAllPrescriptions() throws ServiceException;
}
