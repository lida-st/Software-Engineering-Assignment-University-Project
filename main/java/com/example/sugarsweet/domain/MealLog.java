package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/


import com.example.sugarsweet.util.Tuple2;

import java.util.ArrayList;

/**
 * MealLog represents any registered meal from the Diabetic
 */
public class MealLog{

    //The diabetic performing the registration
    private User user;

    //The ingredients of the meals as well as their quantities
    private ArrayList<Tuple2> ingredientList;

    /**
     * Constructor. Sets user and ingredientList
     *
     * @param user the diabetic of the registration
     * @param ingredientList the ingredients of the meals as well as their quantities
     * @return
     */
    public MealLog(User user,ArrayList<Tuple2> ingredientList){
        this.user=user;
        this.ingredientList=ingredientList;
    }

    /**
     * Null constructor
     */
    public MealLog() { }

    /**
     * Get this log's diabetic operant
     *
     * @return diabetic
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Get this log's ingredients list
     *
     * @return name
     */
    public ArrayList<Tuple2> getIngredientList(){
        return this.ingredientList;
    }

    /**
     * Get this log's sum of carbohydrates
     *
     * @return sum
     */
    public int getTotalCarbohydrates(){
        int sum = 0;
        int quantity = 0;
        int carbohydrates = 0;
        for (Tuple2 m : ingredientList){
            quantity = (Integer) m.z;
            Ingredient ingredient = (Ingredient) m.y;
            carbohydrates = ingredient.getCarbohydratesPerGram();
            sum+=(carbohydrates*quantity);
        }
        return sum;
    }

}

