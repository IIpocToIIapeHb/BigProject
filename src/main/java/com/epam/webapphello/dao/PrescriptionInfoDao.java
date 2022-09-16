package com.epam.webapphello.dao;

import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.DAOException;

import java.util.List;

public interface PrescriptionInfoDao extends Dao<PrescriptionInfo> {
     List<PrescriptionInfo> getPrescriptionsByStatus(final String PrescriptionStatus) throws DAOException;

    List<PrescriptionInfo> getAllPrescriptions() throws DAOException;
}
