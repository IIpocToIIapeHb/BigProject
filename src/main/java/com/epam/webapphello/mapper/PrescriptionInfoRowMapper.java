package com.epam.webapphello.mapper;

import com.epam.webapphello.entity.PrescriptionInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.webapphello.entity.PrescriptionInfo.*;


public class PrescriptionInfoRowMapper implements RowMapper{
    @Override
    public PrescriptionInfo map(ResultSet resultSet) throws SQLException {
        return new PrescriptionInfo(resultSet.getLong(PRESCRIPTION_ID),
                        resultSet.getString(USER_NAME),
                        resultSet.getString(USER_SURNAME),
                        resultSet.getDate(USER_BIRTH),
                        resultSet.getString(MEDICINE_NAME),
                        resultSet.getDate(PRESCRIPTION_VALID_UNTIL),
                        resultSet.getString(PRESCRIPTION_STATUS),
                        resultSet.getInt(PRESCRIPTION_MEDICINE_AMOUNT));
    }

}
