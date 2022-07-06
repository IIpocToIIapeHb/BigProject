package com.epam.webapphello.dao;

import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface PrescriptionInfoDao extends Dao<PrescriptionInfo> {
     List<PrescriptionInfo> getPrescriptionsByStatus(final String PrescriptionStatus) throws DAOException;

    List<PrescriptionInfo> getAllPrescriptions() throws DAOException;
}
