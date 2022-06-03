package com.epam.webapphello.dao;

import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.PositionInfoRowMapper;
import com.epam.webapphello.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PositionInfoDaoImpl extends AbstractDao<PositionInfo> implements PositionInfoDao {

    private static final String GET_POSITION_BY_USERID_AND_ORDERSTATUS =

    "SELECT order_medicine.id, Pharmacy.order.user_id," +
            " user.name,user.surname,order_medicine.order_id, " +
            "Pharmacy.order.creation_date, Pharmacy.order.status," +
            " order_medicine.medicine_id, order_medicine.required_amount," +
            " medicine.name,medicine.path, medicine.with_recipe, medicine.price, " +
            "recipe.id, recipe.status, recipe.valid_until " +

            "FROM Pharmacy.order " +

            "JOIN order_medicine " +
            "ON Pharmacy.order.id=order_medicine.order_id " +

            "JOIN medicine " +
            "ON order_medicine.medicine_id=medicine.id " +

            "JOIN user " +
            "ON user.id=Pharmacy.order.user_id " +

            "LEFT OUTER JOIN   recipe " +
            "ON user.id=recipe.user_id " +

            "and medicine.id=recipe.medicine_id " +
            " WHERE Pharmacy.order.user_id = ? and  Pharmacy.order.status = ?;" ;


    @Override
    public List<PositionInfo> getPositionsByUserIdAndOrderStatus(Long userId, String orderStatus) throws DAOException {
        return executeQuery(GET_POSITION_BY_USERID_AND_ORDERSTATUS,
                userId,
                orderStatus);
    }


    public PositionInfoDaoImpl(Connection connection) {
        super(connection, new PositionInfoRowMapper());
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected Map<String, Object> getFields(PositionInfo item) {
        return null;
    }

    @Override
    public Optional<PositionInfo> getById(Long id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) throws DAOException {

    }

}



