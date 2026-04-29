package com.example.sugarsweet.memorydao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/


import com.example.sugarsweet.dao.*;
import com.example.sugarsweet.domain.*;
import com.example.sugarsweet.util.Tuple;


public class MemoryInitializer extends Initializer {

    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    @Override
    protected void eraseData() {

        for (String user : getUsersDAO().findAll().keySet()) {
            for (Tuple<String, BloodSugarLog> measurement : getMeasurementsDAO().findAll(user)) {
                getMeasurementsDAO().delete(measurement);
            }
            getUsersDAO().delete(user);
        }

        for (String exercise : getExercisesDAO().findAll().keySet()) {
            getExercisesDAO().delete(exercise);
        }

        for (String ingredient : getIngredientsDAO().findAll().keySet()) {
            getIngredientsDAO().delete(ingredient);
        }

    }

    @Override
    public UsersDAO getUsersDAO() {
        return new UsersDAOMemory();
    }

    @Override
    public ExercisesDAO getExercisesDAO() {
        return new ExercisesDAOMemory();
    }

    @Override
    public IngredientsDAO getIngredientsDAO() {
        return new IngredientsDAOMemory();
    }

    @Override
    public MeasurementsDAO getMeasurementsDAO() {
        return new MeasurementsDAOMemory();
    }


}
