package com.epam.webapphello.service;

import com.epam.webapphello.dao.*;
import com.epam.webapphello.entity.*;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceErrorException;
import com.epam.webapphello.exception.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;

public class PositionInfoServiceImpl implements PositionInfoService {

    private DaoHelperFactory daoHelperFactory;
    List<PositionInfo> positions = null;
    private static final String PRESCRIPTION_STATUS_APPROVED = "approved";
    private static final String PRESCRIPTION_STATUS_OVERDUE = "overdue";
    private static final String PRESCRIPTION_STATUS_USED = "used";
    private static final String ORDER_STATUS_NOT_PAID = "not_paid";
    private static final String ORDER_STATUS_PAID = "paid";


    public PositionInfoServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    @Override
    public List<PositionInfo> getPositions(Long userId, String orderStatus) throws ServiceException {
        positions = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PositionInfoDao PositionDao = helper.createPositionInfoDao();
            positions = PositionDao.getPositionsByUserIdAndOrderStatus(userId, orderStatus);
            dateValidation(helper, positions);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return positions;
    }

    public BigDecimal calcTotalPrice() {
        Double totalPrice = 0.0;
        for (PositionInfo position : positions) {
            totalPrice += position.getTotal();
        }
        BigDecimal result = new BigDecimal(totalPrice);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }

    @Override
    public boolean pay(List<PositionInfo> positions, User user, BigDecimal totalPrice) throws ServiceException, ServiceErrorException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            validation(helper, user, totalPrice, positions);

            for (PositionInfo position : positions) {
                updateMedicineStockQuantity(helper, position);
                updatePrescriptionAmount(helper, position);
            }
            updateUserCount(helper, user, totalPrice);
            changeOrderStatusOnPaid(helper, user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    private void updateMedicineStockQuantity(DaoHelper helper, PositionInfo position) throws DAOException {
        MedicineDao medicineDao = helper.createMedicineDao();
        Medicine medicine = medicineDao.getByMedicineId(position.getMedicineId());
        Integer medicineStockQuantity = medicine.getAmount();
        int newMedicineQuantity = medicineStockQuantity - position.getRequiredAmount();
        medicine.setAmount(newMedicineQuantity);
        medicineDao.save(medicine);
    }

    private void updatePrescriptionAmount(DaoHelper helper, PositionInfo position) throws DAOException {
        if (position.getMedicineWithPrescription() == 1) {
            PrescriptionDao recipeDao = helper.createPrescriptionDao();
            Prescription prescription = (Prescription) recipeDao.getById(position.getPrescriptionId());
            int newPrescriptionAmount = prescription.getAmount() - position.getRequiredAmount();
            prescription.setAmount(newPrescriptionAmount);
            if (prescription.getAmount() == 0) {
                prescription.setStatus(PRESCRIPTION_STATUS_USED);
            }
            recipeDao.save(prescription);
        }
    }

    private void updateUserCount(DaoHelper helper, User user, BigDecimal totalPrice) throws DAOException {
        BigDecimal newUserCount = user.getAmount().subtract(totalPrice);
        user.setAmount(newUserCount);
        UserDao userDao = helper.createUserDao();
        userDao.save(user);
    }

    private void changeOrderStatusOnPaid(DaoHelper helper, User user) throws DAOException {
        OrderDao orderDao = helper.createOrderDao();
        Order order = orderDao.findOrderByStatusAndUser(ORDER_STATUS_NOT_PAID, user.getId()).get();
        order.setStatus(ORDER_STATUS_PAID);
        orderDao.save(order);
    }

    @Override
    public List<PositionInfo> getPositions(Long orderId) throws ServiceException {
        positions = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PositionInfoDao PositionDao = helper.createPositionInfoDao();
            positions = PositionDao.getPositionsByOrderId(orderId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return positions;
    }

    private void validation(DaoHelper helper, User user, BigDecimal totalPrice, List<PositionInfo> positions) throws ServiceErrorException, DAOException {
        MedicineDao medicineDao = helper.createMedicineDao();
        for (PositionInfo position : positions) {
            if (position.getMedicineWithPrescription() == 1 && position.getRequiredAmount() > position.getPrescriptionAmount() && position.getPrescriptionAmount() != 0) {
                throw new ServiceErrorException("You exceed limits. Please check your order");
            }
            if (position.getMedicineWithPrescription() == 1 && (position.getPrescriptionStatus() == null || !position.getPrescriptionStatus().equals("approved"))) {
                throw new ServiceErrorException("There are not approved medicines. Please check your order");
            }
            Medicine medicine = medicineDao.getByMedicineId(position.getMedicineId());
            Integer medicineStockQuantity = medicine.getAmount();
            if (position.getRequiredAmount() > medicineStockQuantity) {
                throw new ServiceErrorException("There is no medicine " +
                        position.getMedicineName() + "in the stock in the amount of " +
                        position.getRequiredAmount() + ". Available Quantity " + medicineStockQuantity);
            }
            if (user.getAmount().doubleValue() < totalPrice.doubleValue()) {
                throw new ServiceErrorException("You don't have enough money(((((");
            }

        }
    }

    public void dateValidation(DaoHelper helper, List<PositionInfo> positions) throws DAOException {
        Date currentDate = new Date(System.currentTimeMillis());
        for (PositionInfo position : positions) {
            if (position.getPrescriptionValidUntil() != null && position.getPrescriptionValidUntil().before(currentDate)
                    && position.getPrescriptionStatus().equals(PRESCRIPTION_STATUS_APPROVED)) {
                PrescriptionDao recipeDao = helper.createPrescriptionDao();
                recipeDao.changeStatus(position.getPrescriptionId(), PRESCRIPTION_STATUS_OVERDUE);
            }
        }
    }
}
