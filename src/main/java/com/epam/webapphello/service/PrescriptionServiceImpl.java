package com.epam.webapphello.service;

import com.epam.webapphello.dao.DaoHelper;
import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.dao.PrescriptionDao;
import com.epam.webapphello.entity.Prescription;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.sql.Date;

public class PrescriptionServiceImpl implements PrescriptionService {

    private DaoHelperFactory daoHelperFactory;

    public PrescriptionServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void requestPrescription(Long userId, Long medicineId) throws ServiceException {
        Prescription prescription;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
            prescriptionDao.requestPrescriptionByUserAndMedicine(userId, medicineId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void changePrescriptionStatus(Long prescriptionId, String prescriptionStatus) throws ServiceException {
        String newPrescriptionStatus= null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
            switch (prescriptionStatus){
                case("pending approval"):
                case("extension requested"):
                    break;
                case ("declined"):
                case (""):
                    newPrescriptionStatus = "pending approval";
                    prescriptionDao.changeStatus(prescriptionId, newPrescriptionStatus);
                    break;
                case ("overdue"):
                    newPrescriptionStatus = "extension requested";
                    prescriptionDao.changeStatus(prescriptionId, newPrescriptionStatus);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown prescription status" + prescriptionStatus);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void confirmPrescription(long prescriptionId, int prescriptionMedicineAmount, int prescriptionTerm) throws ServiceException {
        java.util.Date now = new java.util.Date();
        long time =  (long)prescriptionTerm*24*60*60*1000;
        Date prescriptionValidUntil = new Date(now.getTime()+time);

        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
            prescriptionDao.confirmPrescription(prescriptionId, prescriptionMedicineAmount,prescriptionValidUntil);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void extendPrescription(long prescriptionId, int prescriptionTerm) throws ServiceException {
        java.util.Date now = new java.util.Date();
        long time =  (long)prescriptionTerm*24*60*60*1000;
        Date prescriptionValidUntil = new Date(now.getTime()+time);

        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
            prescriptionDao.extendPrescription(prescriptionId,prescriptionValidUntil);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePrescriptionStatusOn(long prescriptionId, String newPrescriptionStatus) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
            prescriptionDao.changeStatus(prescriptionId, newPrescriptionStatus);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}
