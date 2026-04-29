package com.example.sugarsweet.domain;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/


import android.widget.TextView;

import java.util.HashMap;

/**
 * InsulinAdministration represents any advice given to the Diabetic in order to manage their blood sugar levels
 * This class uses polymorphism in order to accommodate all possible advices used by the following classes:
 * 1. BloodSugarLog, 2. MealLog, 3. ExerciseLog
 */
public class InsulinAdministration{

    //The diabetic in need of advice
    private User user;

    /**
     * Constructor. Sets user
     *
     * @param user the diabetic in need of advice
     * @return
     */
    public InsulinAdministration(User user){
        this.user=user;
    }

    /**
     * Null constructor
     */
    public InsulinAdministration() { }

    /**
     * The calculation for the basic insulin dosage.
     *
     * @return the insulin dosage calculated
     */
    public Float dosage(){
        Integer sugarLvl = this.user.getSugarLvl();
        Float sugarLvl2= (float) sugarLvl;
        return (sugarLvl2-100)/70;
    }

    /**
     * The calculation for the insulin dosage needed after a meal.
     *
     * @return the insulin dosage calculated
     */
    public Float food_dosage(int carbs){
        int total = carbs;
        Float dosage = (float) total / 15;
        Float personalFactor = this.user.getPersonalFactor();
        dosage*=personalFactor;
        return dosage/70;
    }

    /**
     * The advice method used in a BloodSugarLog
     * It presents either an advice or an insulin dosage to the user
     *
     * @param bsl the BloodSugarLog object needed for the calculations
     * @param textView the text view in which the advice will be written and shown to the user
     * @return
     */
    public void advice(BloodSugarLog bsl, TextView textView){
        this.user=bsl.getUser();
        Integer sugarLvl = this.user.getSugarLvl();
        if (sugarLvl < 70){
            textView.setText("Consume one of the following: "+"\n1.Orange juice with a slice of bread"+"\n2.One teaspoon of sugar with a slice of bread"+"\n3.One teaspoon of honey with a slice of bread");
        } else if (sugarLvl <= 170){
            textView.setText("Great Job! Your blood sugar levels are ideal!");
        }else {
            Float dosage=dosage();
            textView.setText("The insulin dosage you need to administer is: "+ dosage);
        }
    }

    /**
     * The advice method used in an ExerciseLog
     * It presents either an advice or an insulin dosage to the user
     *
     * @param el the ExerciseLog object needed for the calculations
     * @param ex the list of registered exercises needed for the calculations
     * @param textView the text view in which the advice will be written and shown to the user
     * @return
     */
    public void advice(ExerciseLog el, HashMap<String,Exercise> ex,TextView textView){
        this.user=el.getUser();
        Integer sugarLvl = this.user.getSugarLvl();
        if (sugarLvl < 70){
            textView.setText("Consume one of the following: "+"\n1.Orange juice with a slice of bread"+"\n2.One teaspoon of sugar with a slice of bread"+"\n3.One teaspoon of honey with a slice of bread");
        } else if (sugarLvl <= 170){
            int duration = el.getExerciseDuration();
            for (HashMap.Entry m:ex.entrySet()){
                if (m.getKey().equals(el.getExerciseName())){
                    Exercise exercise = (Exercise) m.getValue();
                    int consumptionPerMinute = exercise.getConsumptionPerMinute();
                    if ((consumptionPerMinute*duration)<=40){
                        textView.setText("Consume half a slice of bread");
                    } else if ((consumptionPerMinute*duration)<=75){
                        textView.setText("Consume a slice of bread");
                    } else {
                        textView.setText("Consume one and a half slices of bread");
                    }
                    break;
                }
            }
        }else {
            int duration = el.getExerciseDuration();
            for (HashMap.Entry m:ex.entrySet()){
                if (m.getKey().equals(el.getExerciseName())){
                    Exercise exercise = (Exercise) m.getValue();
                    int consumptionPerMinute = exercise.getConsumptionPerMinute();
                    sugarLvl -= consumptionPerMinute*duration;
                    this.user.setSugarLvl(sugarLvl);
                    if (sugarLvl > 170){
                        Float dosage=dosage();
                        textView.setText("The insulin dosage you need to administer is: "+dosage.toString());
                    } else {
                        textView.setText("You do not need to eat anything or administer any insulin. Have a great workout!");
                    }
                    break;
                }
            }
        }
    }

    /**
     * The advice method used in an ExerciseLog when the option "OTHER" is chosen
     * It presents either an advice or an insulin dosage to the user
     *
     * @param option the choice of the user for the intensity of the exercise
     * @param duration the duration of the exercise used for the calculations
     * @param textView the text view in which the advice will be written and shown to the user
     * @return
     */
    public void advice(int option, int duration,TextView textView){
        int consumptionPerMinute;
        textView.setText("Warning: Due to the lack of information the calculations are not precise.");
        switch (option) {
            case 1:
                consumptionPerMinute = 1;
                break;
            case 2:
                consumptionPerMinute = 3;
                break;
            default:
                consumptionPerMinute = 5;
                break;
        }
        Integer sugarLvl = this.user.getSugarLvl();
        if (sugarLvl < 70){
            textView.setText("Consume one of the following: "+"\n1.Orange juice with a slice of bread"+"\n2.One teaspoon of sugar with a slice of bread"+"\n3.One teaspoon of honey with a slice of bread");
        } else if (sugarLvl <= 170){
            if ((consumptionPerMinute*duration)<=40){
                textView.setText("Consume half a slice of bread");
            } else if ((consumptionPerMinute*duration)<=75){
                textView.setText("Consume a slice of bread");
            } else {
                textView.setText("Consume one and a half slices of bread");
            }
        }else {
            sugarLvl -= consumptionPerMinute*duration;
            this.user.setSugarLvl(sugarLvl);
            if (sugarLvl > 170){
                Float dosage=dosage();
                textView.setText("The insulin dosage you need to administer is: "+ dosage.toString());
            } else {
                textView.setText("You do not need to eat anything or administer any insulin. Have a great workout!");
            }
        }
    }

    /**
     * The advice method used in a MealLog
     * It presents either an advice or an insulin dosage to the user
     *
     * @param ml the MealLog object needed for the calculations
     * @param textView the text view in which the advice will be written and shown to the user
     * @return
     */
    public void advice(MealLog ml,TextView textView){
        this.user=ml.getUser();
        Integer sugarLvl = this.user.getSugarLvl();
        Float dosage = food_dosage(ml.getTotalCarbohydrates());
        if (sugarLvl <= 170){
            if (sugarLvl < 70){
                textView.setText("Consume one of the following: "+"\n1.Orange juice with a slice of bread"+"\n2.One teaspoon of sugar with a slice of bread"+"\n3.One teaspoon of honey with a slice of bread");
            }
            textView.setText("The insulin dosage you need to administer is: "+ dosage.toString());
        }else {
            Float finale=dosage();
            textView.setText("The insulin dosage you need to administer is: "+ (dosage+finale));
        }
    }

}

