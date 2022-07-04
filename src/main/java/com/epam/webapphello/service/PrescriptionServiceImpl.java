package com.epam.webapphello.service;

import com.epam.webapphello.dao.DaoHelper;
import com.epam.webapphello.dao.DaoHelperFactory;
import com.epam.webapphello.dao.PrescriptionDao;
import com.epam.webapphello.entity.Recipe;
import com.epam.webapphello.exception.DAOException;
import com.epam.webapphello.exception.ServiceException;

import java.sql.Date;

public class PrescriptionServiceImpl implements PrescriptionService {

    private DaoHelperFactory daoHelperFactory;

    public PrescriptionServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }



//    @Override
//    public Recipe getRecipe(Long userId, Long medicineId) throws ServiceException {
//
//        Optional<Recipe> recipe = null;
//        try (DaoHelper helper = daoHelperFactory.create()) {
//            RecipeDao recipeDao = helper.createRecipeDao();
//            recipe = recipeDao.findRecipeByUserAndMedicine(userId, medicineId);
//        } catch (DAOException e) {
//            throw new ServiceException(e);
//        }
//        return recipe.get();
//    }

    public void requestRecipe(Long userId, Long medicineId) throws ServiceException {
        Recipe recipe;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao recipeDao = helper.createPrescriptionDao();
            recipeDao.requestRecipeByUserAndMedicine(userId, medicineId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void changeRecipeStatus(Long recipeId, String recipeStatus) throws ServiceException {
        String newRecipeStatus= null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            PrescriptionDao recipeDao = helper.createPrescriptionDao();


            switch (recipeStatus){
                case("pending approval"):
                case("extension requested"):
                    break;
                case ("declined"):
                case (""):
                    newRecipeStatus = "pending approval";
                    recipeDao.changeStatus(recipeId, newRecipeStatus);
                    break;
                case ("overdue"):
                    newRecipeStatus = "extension requested";
                    recipeDao.changeStatus(recipeId, newRecipeStatus);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown recipe status" + recipeStatus);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void confirmPrescription(long prescriptionId, int prescriptionMedicineAmount, int prescriptionTerm) throws ServiceException {

        java.util.Date now = new java.util.Date();
        long millis=System.currentTimeMillis();
        long time =  (long)prescriptionTerm*24*60*60*1000;
        long prescriptionTimeInMilliseconds = time + millis;
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
