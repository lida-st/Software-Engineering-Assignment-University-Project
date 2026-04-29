package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import java.io.Serializable;

/**
 * User represents any user that accesses SugarSweet
 * It is the superclass of the Administrator and the Diabetic classes
 */
public class User implements Serializable {

    // This user's unique personal password
    private String password;

    // This user's personal sugar factor
    private Float personalFactor;

    // This user's last registered sugar level
    private Integer sugarLvl;

    /**
     * Constructor. Sets password, personalFactor and sugarLvl
     *
     * @param password the password registered
     * @param personalFactor the personal factor registered
     * @param sugarLvl the sugar level registered
     * @return
     */
    public User(String password,Float personalFactor,Integer sugarLvl){
        this.password=password;
        this.personalFactor=personalFactor;
        this.sugarLvl=sugarLvl;
    }

    /**
     * Null constructor
     */
    public User() { }

    /**
     * Constructor. Sets password
     *
     * @param password the password registered
     * @return
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get this user's unique password
     *
     * @return password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Constructor. Sets personalFactor
     *
     * @param personalFactor the perosnal factor registered
     * @return
     */
    public void setPersonalFactor(Float personalFactor) {
        this.personalFactor = personalFactor;
    }

    /**
     * Get this user's personal factor
     *
     * @return personal factor
     */
    public Float getPersonalFactor(){
        return this.personalFactor;
    }

    /**
     * Constructor. Sets sugarLvl
     *
     * @param sugarLvl the sugar level registered
     * @return
     */
    public void setSugarLvl(Integer sugarLvl) {
        this.sugarLvl = sugarLvl;
    }

    /**
     * Get this user's last registered sugar level
     *
     * @return sugar level
     */
    public Integer getSugarLvl(){
        return this.sugarLvl;
    }

    public String toString(){
        return String.format("%-33s %32s\n%-33s %32s\n%-33s %32s\n","Password:",this.password,"Personal Factor:",this.personalFactor,"Sugar Level:",this.sugarLvl);
    }

}


