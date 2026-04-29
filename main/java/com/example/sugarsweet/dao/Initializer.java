package com.example.sugarsweet.dao;
/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/
import com.example.sugarsweet.domain.*;
import com.example.sugarsweet.util.*;

import java.time.LocalDate;

public abstract class Initializer {

    /**
     * Εισάγει τα δοκιμαστικά δεδομένα.
     */
    public void prepareData()
    {
        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();

        UsersDAO usersDAO = getUsersDAO();

        usersDAO.save("13456789",new Administrator("13456789",null,null));
        usersDAO.save("12345678",new Diabetic("12345678", 0.14F,72));
        usersDAO.save("09876543",new Diabetic("09876543", 0.54F,100));

        ExercisesDAO exercisesDAO = getExercisesDAO();

        exercisesDAO.save("WALKING",new Exercise("WALKING",10));
        exercisesDAO.save("RUNNING",new Exercise("RUNNING",5));
        exercisesDAO.save("SLOW WALKING",new Exercise("SLOW WALKING",1));

        IngredientsDAO ingredientsDAO = getIngredientsDAO();

        ingredientsDAO.save("APPLE",new Ingredient("APPLE",10));
        ingredientsDAO.save("CHICKEN",new Ingredient("CHICKEN",20));
        ingredientsDAO.save("BEEF",new Ingredient("BEEF",60));
        ingredientsDAO.save("PORK",new Ingredient("PORK",100));

        MeasurementsDAO measurementsDAO = getMeasurementsDAO();

        BloodSugarLog bsl = new BloodSugarLog(new User("12345678",0.14F,70), LocalDate.parse("2025-01-01"));
        Tuple<String,BloodSugarLog> t = new Tuple("12345678",bsl);
        measurementsDAO.save(t);
        bsl = new BloodSugarLog(new User("12345678",0.14F,109), LocalDate.parse("2025-01-15"));
        t = new Tuple("12345678",bsl);
        measurementsDAO.save(t);
        bsl = new BloodSugarLog(new User("12345678",0.14F,101), LocalDate.parse("2025-01-12"));
        t = new Tuple("12345678",bsl);
        measurementsDAO.save(t);
        bsl = new BloodSugarLog(new User("09876543",0.54F,123), LocalDate.parse("2025-11-28"));
        t = new Tuple("09876543",bsl);
        measurementsDAO.save(t);
        bsl = new BloodSugarLog(new User("12345678",0.14F,72), LocalDate.parse("2025-12-04"));
        t = new Tuple("12345678",bsl);
        measurementsDAO.save(t);

    }

    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    protected abstract void eraseData();

    public abstract UsersDAO getUsersDAO();

    public abstract ExercisesDAO getExercisesDAO();

    public abstract IngredientsDAO getIngredientsDAO();

    public abstract MeasurementsDAO getMeasurementsDAO();

}
