package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

/**
 * Diabetic represent the diabetic users that access SugarSweet
 * It is one of the two subclasses of the User class
 */
public class Diabetic extends User{

    /**
     * Constructor. Sets password, personalFactor and sugarLvl
     *
     * @param password the password registered
     * @param personalFactor the perosnal factor registered
     * @param sugarLvl the sugar level registered
     * @return
     */
    public Diabetic(String password,Float personalFactor,Integer sugarLvl){
        super(password,personalFactor,sugarLvl);
    }

    /**
     * Null constructor
     */
    public Diabetic() { }

}
