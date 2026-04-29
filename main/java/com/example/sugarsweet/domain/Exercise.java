package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

/**
 * Exercise represents any registered exercise from the Administrators
 */
public class Exercise{

    // This exercise's unique name
    private String name;

    // This exercise's glucose consumption per minute
    private int consumptionPerMinute = 0;

    /**
     * Constructor. Sets name and consumptionPerMinute
     *
     * @param name the name registered
     * @param consumptionPerMinute the glucose consumption registered
     * @return
     */
    public Exercise(String name,int consumptionPerMinute){
        this.name=name;
        this.consumptionPerMinute=consumptionPerMinute;
    }

    /**
     * Null constructor
     */
    public Exercise() { }

    /**
     * Constructor. Sets name
     *
     * @param name the name registered
     * @return
     */
    public void setExerciseName(String name) {
        this.name = name;
    }

    /**
     * Get this exercise's unique name
     *
     * @return name
     */
    public String getExerciseName(){
        return name;
    }

    /**
     * Constructor. Sets consumptionPerMinute
     *
     * @param consumptionPerMinute the glusose consumption registered
     * @return
     */
    public void setConsumptionPerMinute(int consumptionPerMinute) {
        this.consumptionPerMinute = consumptionPerMinute;
    }

    /**
     * Get this exercise's glucose consumption per minute
     *
     * @return consumption per minute
     */
    public int getConsumptionPerMinute(){
        return consumptionPerMinute;
    }

    public String toString(){
        return String.format("%-33s %32s\n%-33s %32s\n","Exercise Name:",this.name,"Consumption Per Minute:",this.consumptionPerMinute);
    }

}


