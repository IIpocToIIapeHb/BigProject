package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.MedicineRowMapper;
import com.epam.webapphello.mapper.RowMapper;
import com.epam.webapphello.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class MedicineDaoImpl extends AbstractDao<Medicine> implements MedicineDao{

    public MedicineDaoImpl (Connection connection) {
        super(connection, new MedicineRowMapper());
    }

    @Override
    protected String getTableName() {
        return Medicine.TABLE;
    }

    @Override
    protected Map<String, Object> getFields(Medicine item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Medicine.AMOUNT, item.getAmount());
        return fields;
    }




  public Medicine getByMedicineId(Long id) throws DAOException{
        String request ="select * from medicine where id = ?;";
        Medicine entity =  executeForSingleResult(request, id).get();
        return  entity;
    }

}
