package com.example.sugarsweet.dao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.domain.BloodSugarLog;
import com.example.sugarsweet.util.Tuple;

import java.util.ArrayList;

public interface MeasurementsDAO {

    /**
     * Διαγράφει μια μέτρηση επιπέδου σακχάρου.
     * @param entity Η μέτρηση
     */
    void delete(Tuple<String,BloodSugarLog> entity);

    /**
     * Επιστρέφει όλες τις μετρήσεις ενός χρήστη.
     * @param id Ο κωδικός του χρήστη
     * @return Οι μετρήσεις
     */
    ArrayList<Tuple<String,BloodSugarLog>> findAll(String id);

    /**
     * Αποθηκεύει μια μέτρηση.
     * @param entity Η μέτρηση
     */
    void save(Tuple<String,BloodSugarLog> entity);

    /**
     * Βρίσκει μια μέτρηση με βάση τον κωδικό του χρήστη που την έκανε.
     * @param BloodSugarLogId Ο κωδικός του χρήστη
     * @return Η μέτρηση που βρέθηκε ή null
     */
    BloodSugarLog find(String BloodSugarLogId);

}
