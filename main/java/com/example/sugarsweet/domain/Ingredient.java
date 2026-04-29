package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

/**
 * Ingredient represents any registered basic food ingredient from the Administrators
 */
public class Ingredient{

    // This ingredient's unique name
    private String name;

    // This ingredient's contained carbogydrates per gram
    private int carbohydratesPerGram = 0;

    /**
     * Constructor. Sets name and carbohydratesPerGram
     *
     * @param name the name registered
     * @param carbohydratesPerGram the contained carbohydrates registered
     * @return
     */
    public Ingredient(String name,int carbohydratesPerGram){
        this.name=name;
        this.carbohydratesPerGram=carbohydratesPerGram;
    }

    /**
     * Null constructor
     */
    public Ingredient() { }

    /**
     * Constructor. Sets name
     *
     * @param name the name registered
     * @return
     */
    public void setIngredientName(String name) {
        this.name = name;
    }

    /**
     * Get this ingredient's unique name
     *
     * @return name
     */
    public String getIngredientName(){
        return name;
    }

    /**
     * Constructor. Sets carbohydratesPerGram
     *
     * @param carbohydratesPerGram the contained carbohydrates registered
     * @return
     */
    public void setCarbohydratesPerGram(int carbohydratesPerGram) {
        this.carbohydratesPerGram = carbohydratesPerGram;
    }

    /**
     * Get this ingredient's contained carbohydrates per gram
     *
     * @return carbohydrates per gram
     */
    public int getCarbohydratesPerGram(){
        return carbohydratesPerGram;
    }

    public String toString(){
        return String.format("%-33s %32s\n%-33s %32s\n","Ingredient Name:",this.name,"Carbohydrates Per Gram:",this.carbohydratesPerGram);
    }

}


