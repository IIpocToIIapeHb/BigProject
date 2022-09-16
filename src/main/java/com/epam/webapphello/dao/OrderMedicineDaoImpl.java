package com.epam.webapphello.dao;

import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.OrderMedicineRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class OrderMedicineDaoImpl extends AbstractDao<OrderMedicine> implements OrderMedicineDao {

    private static final String FIND_BY_ORDER_AND_MEDICINE = "select * from order_medicine where order_id = ? and medicine_id = ?";
    private static final String CHANGE_MEDICINE_ORDER_AMOUNT ="UPDATE order_medicine SET required_amount = ? WHERE (id = ?);";

    public OrderMedicineDaoImpl(Connection connection) {
        super(connection, new OrderMedicineRowMapper());
    }

    @Override
    protected String getTableName() {
        return OrderMedicine.TABLE;
    }

    @Override
    protected Map<String, Object> getFields(OrderMedicine item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(OrderMedicine.MEDICINE_ID, item.getMedicineId());
        fields.put(OrderMedicine.REQUIRED_AMOUNT, item.getRequiredAmount());
        fields.put(OrderMedicine.ORDER_ID, item.getOrderId());
        return fields;
    }



    @Override
    public Optional<OrderMedicine> findOrderMedicineByUserAndMedicine(Long orderId, Long medicineId) throws DAOException {
        return executeForSingleResult(FIND_BY_ORDER_AND_MEDICINE,
                orderId,
                medicineId);
    }

    @Override
    public boolean changeMedicineOrderAmount(Long orderMedicineId, Integer medicineNumber) throws DAOException {
        return executeUpdate(CHANGE_MEDICINE_ORDER_AMOUNT,
                medicineNumber,
                orderMedicineId);
    }


}


