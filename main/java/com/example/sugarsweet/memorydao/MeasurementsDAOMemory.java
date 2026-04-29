package com.example.sugarsweet.memorydao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.dao.MeasurementsDAO;
import com.example.sugarsweet.domain.BloodSugarLog;
import com.example.sugarsweet.util.Tuple;

import java.util.ArrayList;

public class MeasurementsDAOMemory implements MeasurementsDAO {
    protected static ArrayList<Tuple<String,BloodSugarLog>> entities = new ArrayList<Tuple<String,BloodSugarLog>>();

    /**
     * Διαγράφει μια μέτρηση επιπέδου σακχάρου.
     * @param entity Η μέτρηση
     */
    public void delete(Tuple<String,BloodSugarLog> entity) {
        entities.remove(entity);
    }

    /**
     * Επιστρέφει όλες τις μετρήσεις ενός χρήστη.
     * @param id Ο κωδικός του χρήστη
     * @return Οι μετρήσεις
     */
    public ArrayList<Tuple<String,BloodSugarLog>> findAll(String id) {
        ArrayList<Tuple<String,BloodSugarLog>> result = new ArrayList<Tuple<String,BloodSugarLog>>();
        for (Tuple<String,BloodSugarLog> m : entities){
            if (m.x.equals(id)){
                result.add(m);
            }
        }
        return result;
    }

    /**
     * Αποθηκεύει μια μέτρηση.
     * @param entity Η μέτρηση
     */
    public void save(Tuple<String,BloodSugarLog> entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει μια μέτρηση με βάση τον κωδικό του χρήστη που την έκανε.
     * @param id Ο κωδικός του χρήστη
     * @return Η μέτρηση που βρέθηκε ή null
     */
    public BloodSugarLog find(String id)
    {
        for(Tuple<String,BloodSugarLog> bsl : entities) {
            if (bsl.x.equals(id)) {
                return (BloodSugarLog) bsl.y;
            }
        }
        return null;
    }

}
