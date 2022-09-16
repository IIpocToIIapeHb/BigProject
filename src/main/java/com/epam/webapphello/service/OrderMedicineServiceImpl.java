package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.Prescription;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.util.Optional;

public class OrderMedicineServiceImpl implements OrderMedicineService {

    private DaoHelperFactory daoHelperFactory;
    private static final String PRESCRIPTION_STATUS_PENDING_APPROVAL = "pending approval";


    public OrderMedicineServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public void save(OrderMedicine orderMedicine, boolean medicineWithPrescription, Long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.save(orderMedicine);
            createPrescriptionIfAbsent(helper,userId,medicineWithPrescription,orderMedicine.getMedicineId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<OrderMedicine> findOrderMedicine(Long orderId, Long medicineId)  throws ServiceException {
        Optional<OrderMedicine> orderMedicine = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicine = orderMedicineDao.findOrderMedicineByUserAndMedicine(orderId, medicineId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderMedicine;
    }

    @Override
    public void addMedicineOrderAmount(Long orderMedicineId, Integer oldMedicineAmount,Integer addMedicineAmount)  throws ServiceException {
        Integer medicineNumber = oldMedicineAmount+addMedicineAmount;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.changeMedicineOrderAmount(orderMedicineId, medicineNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeMedicine(Long orderMedicineId, Long prescriptionId, String prescriptionStatus) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.removeById(orderMedicineId);
            if (prescriptionStatus.equals(PRESCRIPTION_STATUS_PENDING_APPROVAL) || prescriptionStatus.isEmpty()){
                PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
                prescriptionDao.removeById(prescriptionId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void createPrescriptionIfAbsent(DaoHelper helper, Long userId, boolean medicineWithPrescription, Long medicineId) throws DAOException {

        PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
        if (medicineWithPrescription) {
            Optional<Prescription> prescription= prescriptionDao.findPrescriptionByUserAndMedicineAndUnwantedStatus(userId, medicineId,"used");
            if (prescription.isPresent()){
            } else {
                prescriptionDao.saveEmptyPrescription(userId,medicineId);
            }
        }
    }
}

