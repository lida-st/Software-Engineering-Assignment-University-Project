package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/


/**
 * ExerciseLog represents any registered exercise from the Diabetic
 */
public class ExerciseLog{

    //The diabetic performing the registration
    private User user;

    //The name of the registered exercise
    private String name;

    //The duration of the exercise in minutes
    private int duration;


    /**
     * Constructor. Sets user, name and duration
     *
     * @param user the diabetic of the registration
     * @param name the name of the registered exercise
     * @param duration the duration of the exercise
     * @return
     */
    public ExerciseLog(User user,String name,int duration){
        this.user=user;
        this.name=name;
        this.duration=duration;
    }

    /**
     * Null constructor
     */
    public ExerciseLog() { }

    /**
     * Get this log's diabetic operant
     *
     * @return diabetic
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Get this log's exercise name
     *
     * @return name
     */
    public String getExerciseName(){
        return this.name;
    }

    /**
     * Get this log's duration in minutes
     *
     * @return duration
     */
    public int getExerciseDuration(){
        return this.duration;
    }

    public String toString(){
        return String.format("%-33s %32s\n%-33s %32s\n%-33s %32s\n%-33s %32s\n%-33s %32d","Password:",this.user.getPassword(),"Personal Factor:",this.user.getPersonalFactor(),"Sugar Level:",this.user.getSugarLvl(),"Exercise Name:",this.name,"Duration of the Exercise:",this.duration);
    }

}

