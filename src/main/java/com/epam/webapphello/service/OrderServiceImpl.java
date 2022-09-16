package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.Order;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.Prescription;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private DaoHelperFactory daoHelperFactory;
    private static final String ORDER_STATUS_NOT_PAID = "not_paid";
    private static final String DELIVERY_ORDER_STATUS_PERFORMED = "performed";


    public OrderServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<Order> findOrderByStatusAndUser(final String status, final Long userId) throws ServiceException {
        Optional<Order> order = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao orderDao = helper.createOrderDao();
            order = orderDao.findOrderByStatusAndUser(status,userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    @Override
    public void save(Order order, Long medicineId, Integer requiredAmount, Long userId, boolean isPrescriptionRequired) throws ServiceException {

        DaoHelper helper = null;
        try {
            helper = daoHelperFactory.create();
            helper.startTransaction();

            OrderDao orderDaoSave = helper.createOrderDao();
            orderDaoSave.save(order);

            OrderDao orderDaoFind = helper.createOrderDao();
            Optional<Order> orderWithId = orderDaoFind.findOrderByStatusAndUser(ORDER_STATUS_NOT_PAID,order.getUserId());


            OrderMedicine orderMedicine = new OrderMedicine(medicineId, requiredAmount,orderWithId.get().getId());
            OrderMedicineDao orderMedicineDao = helper.createOrderMedicineDao();
            orderMedicineDao.save(orderMedicine);

            createPrescriptionIfAbsent(helper,userId,isPrescriptionRequired,orderMedicine.getMedicineId());
            helper.endTransaction();
        } catch (DAOException e) {
            if (helper!=null){
                helper.rollback();
            }
            throw new ServiceException(e);
        } finally {
            if (helper!=null){
                helper.close();
            }
        }
    }

    @Override
    public void performOrder(long orderId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao orderDao = helper.createOrderDao();
            orderDao.changeOrderDeliveryStatus(DELIVERY_ORDER_STATUS_PERFORMED, orderId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void createPrescriptionIfAbsent(DaoHelper helper, Long userId, boolean isPrescriptionRequired, Long medicineId) throws DAOException {
        PrescriptionDao prescriptionDao = helper.createPrescriptionDao();
        if (isPrescriptionRequired) {
            Optional<Prescription> prescription= prescriptionDao.findPrescriptionByUserAndMedicineAndUnwantedStatus(userId, medicineId,"used");
            if (!prescription.isPresent()) {
                prescriptionDao.saveEmptyPrescription(userId,medicineId);
            }
        }
    }
}



