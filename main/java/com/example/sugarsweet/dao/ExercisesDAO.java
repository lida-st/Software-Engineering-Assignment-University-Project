package com.example.sugarsweet.dao;

import com.example.sugarsweet.domain.Exercise;

import java.util.HashMap;
public interface ExercisesDAO {

    /**
     * Διαγράφει μια αθλητική δραστηριότητα.
     * @param entity Το όνομα της αθλητικής δραστηριότητας
     */
    void delete(String entity);

    /**
     * Επιστρέφει όλες τις αθλητικές δραστηριότητες
     * @return Οι αθλητικές δραστηριότητες
     */
    HashMap <String,Exercise> findAll();

    /**
     * Αποθηκεύει μια αθλητική δραστηριότητα.
     * @param id Το όνομα της αθλητικής δραστηριότητας
     * @param entity Η αθλητική δραστηριότητα
     */
    void save(String id,Exercise entity);

    /**
     * Βρίσκει μια αθλητική δραστηριότητα με βάση το όνομά της.
     * @param ExerciseId Το όνομα της αθλητικής δραστηριότητας
     * @return Η αθλητική δραστηριότητα που βρέθηκε ή null
     */
    Exercise find(String ExerciseId);

}
