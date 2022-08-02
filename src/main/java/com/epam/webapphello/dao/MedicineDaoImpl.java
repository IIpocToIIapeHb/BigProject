package com.epam.webapphello.dao;

import com.epam.webapphello.entity.Medicine;
import com.epam.webapphello.entity.MedicineCategory;
import com.epam.webapphello.entity.OrderMedicine;
import com.epam.webapphello.entity.User;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.mapper.MedicineRowMapper;
import com.epam.webapphello.mapper.RowMapper;
import com.epam.webapphello.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MedicineDaoImpl extends AbstractDao<Medicine> implements MedicineDao{

    public MedicineDaoImpl (Connection connection) {
        super(connection, new MedicineRowMapper());
    }

    private static final String FIND_BY_NAME = "select * from medicine where name = ?";
    private static final String FIND_CATEGORY_ID_BY_NAME = "select * from medicine_category where name = ?";
    private static final String FIND_CATEGORY_NAME_BY_ID = "select * from medicine_category where id = ?";

    @Override
    protected String getTableName() {
        return Medicine.TABLE;
    }

    @Override
    protected Map<String, Object> getFields(Medicine item) {
        Map<String, Object> fields = new LinkedHashMap<>();
       // fields.put(Medicine.MEDICINE_ID, item.getId());
        fields.put(Medicine.NAME, item.getName());
        fields.put(Medicine.DOSAGE, item.getDosage());
        fields.put(Medicine.WITH_RECIPE, item.getWithRecipe());
        fields.put(Medicine.FORM, item.getForm());
        fields.put(Medicine.AMOUNT, item.getAmount());
        fields.put(Medicine.PACKAGE_AMOUNT, item.getPackageAmount());
        fields.put(Medicine.PRICE, item.getPrice());
        fields.put(Medicine.CATEGORY_ID, item.getCategoryId());
        fields.put(Medicine.IMAGE_PATH, item.getPath());
        return fields;
    }



  public Medicine getByMedicineId(Long id) throws DAOException{
        String request ="select * from medicine where id = ?;";
        Medicine entity =  executeForSingleResult(request, id).get();
        return  entity;
    }

    @Override
    public List<Medicine> findProductByName(String searchingProduct) throws DAOException {
        List<Medicine> foundProduct = executeQuery(FIND_BY_NAME,searchingProduct);
        return foundProduct;
    }



    @Override
    public Long findMedicineCategoryId(String medicineCategory) throws DAOException {
        Long categoryId=null;
        try (PreparedStatement statement = createStatement(FIND_CATEGORY_ID_BY_NAME, medicineCategory);
             ResultSet resultSet = statement.executeQuery()) {
            if(resultSet.next()) {
                categoryId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return categoryId;
    }

    @Override
    public String findMedicineCategoryName(long medicineCategoryId) throws DAOException {
        String categoryName=null;
        try (PreparedStatement statement = createStatement(FIND_CATEGORY_NAME_BY_ID, medicineCategoryId);
             ResultSet resultSet = statement.executeQuery()) {
            if(resultSet.next()) {
                categoryName = resultSet.getString(2);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return categoryName;
    }

}
