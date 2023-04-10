package com.epam.webapphello.dao;

import com.epam.webapphello.connection.ProxyConnection;
import com.epam.webapphello.entity.PositionInfo;
import com.epam.webapphello.entity.PrescriptionInfo;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.PositionInfoRowMapper;
import com.epam.webapphello.mapper.PrescriptionInfoRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class PrescriptionInfoDaoImpl extends AbstractDao<PrescriptionInfo> implements PrescriptionInfoDao {

    private static final String GET_PRESCRIPTIONS_BY_STATUS =
    "SELECT user.name, user.surname, user.birth, medicine.name, recipe.id, recipe.valid_until, recipe.status, recipe.amount"+
    " FROM Recipe"+
    " JOIN user"+
    " ON user.id=recipe.user_id"+
    " JOIN medicine"+
    " ON medicine.id=recipe.medicine_id"+
    " WHERE status = ?;";

    private static final String GET_ALL_PRESCRIPTIONS =
            "SELECT user.name, user.surname, user.birth, medicine.name, recipe.id, recipe.valid_until, recipe.status, recipe.amount"+
                    " FROM Recipe"+
                    " JOIN user"+
                    " ON user.id=recipe.user_id"+
                    " JOIN medicine"+
                    " ON medicine.id=recipe.medicine_id";

    public PrescriptionInfoDaoImpl(Connection connection) {
        super(connection, new PrescriptionInfoRowMapper());
    }
    @Override
    public List<PrescriptionInfo> getPrescriptionsByStatus(String PrescriptionStatus) throws DAOException {
        return executeQuery(GET_PRESCRIPTIONS_BY_STATUS,
                PrescriptionStatus);
    }

    @Override
    public List<PrescriptionInfo> getAllPrescriptions() throws DAOException {
        return executeQuery(GET_ALL_PRESCRIPTIONS);
    }


    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected Map<String, Object> getFields(PrescriptionInfo item) {
        return null;
    }
}
