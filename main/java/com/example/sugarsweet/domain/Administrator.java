package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

/**
 * Administrator represent the administrator group users that access SugarSweet
 * It is one of the two subclasses of the User class
 */
public class Administrator extends User{

    /**
     * Constructor. Sets password, personalFactor and sugarLvl
     *
     * @param password the password registered
     * @param personalFactor the perosnal factor registered
     * @param sugarLvl the sugar level registered
     * @return
     */
    public Administrator(String password,Float personalFactor,Integer sugarLvl){
        super(password,personalFactor,sugarLvl);
    }

    /**
     * Null constructor
     */
    public Administrator() { }

}
