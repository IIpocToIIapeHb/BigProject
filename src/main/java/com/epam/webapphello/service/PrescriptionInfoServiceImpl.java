package com.epam.webapphello.service;


import com.epam.webapphello.dao.DaoHelper;
import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.dao.PrescriptionInfoDao;
import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;
import java.util.List;

public class PrescriptionInfoServiceImpl implements PrescriptionInfoService {

    private DaoHelperFactory daoHelperFactory;


    public PrescriptionInfoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public List<PrescriptionInfo> getAllByStatus(String prescriptionStatus) throws ServiceException {
        PrescriptionInfo.COUNTER=0;
        List<PrescriptionInfo> prescriptions = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionInfoDao prescriptionInfoDao = helper.createPrescriptionInfoDao();
            prescriptions = prescriptionInfoDao.getPrescriptionsByStatus(prescriptionStatus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return prescriptions;
    }

    @Override
    public List<PrescriptionInfo> getAllPrescriptions() throws ServiceException {
        PrescriptionInfo.COUNTER=0;
        List<PrescriptionInfo> prescriptions = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionInfoDao prescriptionInfoDao = helper.createPrescriptionInfoDao();
            prescriptions = prescriptionInfoDao.getAllPrescriptions();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return prescriptions;
    }
}
