package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/


import java.time.LocalDate;

/**
 * BloodSugarLog represents any registered blood sugar measurement from the Diabetic
 */
public class BloodSugarLog{

    //The diabetic performing the registration
    private User user;

    //The date of the registration
    private LocalDate logDate;

    /**
     * Constructor. Sets user and logDate
     *
     * @param user the diabetic of the registration
     * @param logDate the date of the registration
     * @return
     */
    public BloodSugarLog(User user,LocalDate logDate){
        this.user=user;
        this.logDate=logDate;
    }

    /**
     * Null constructor
     */
    public BloodSugarLog() { }

    /**
     * Get this log's diabetic operant
     *
     * @return diabetic
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Get this log's date of registration
     *
     * @return date
     */
    public LocalDate getLogDate(){
        return this.logDate;
    }

    public String toString(){
        return String.format("%-33s %32s\n%-33s %32s\n%-33s %32s\n%-33s %32s","Password:",this.user.getPassword(),"Personal Factor:",this.user.getPersonalFactor(),"Sugar Level:",this.user.getSugarLvl(),"Date of Measurement:",this.logDate);
    }

}

