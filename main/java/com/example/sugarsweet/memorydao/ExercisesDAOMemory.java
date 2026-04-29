package com.example.sugarsweet.memorydao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.dao.ExercisesDAO;
import com.example.sugarsweet.domain.Exercise;

import java.util.HashMap;

public class ExercisesDAOMemory implements ExercisesDAO {
    protected static HashMap<String, Exercise> entities = new HashMap <String,Exercise>();

    /**
     * Διαγράφει μια αθλητική δραστηριότητα.
     * @param entity Το όνομα της αθλητικής δραστηριότητας
     */
    public void delete(String entity) {
        entities.remove(entity);
    }

    /**
     * Επιστρέφει όλες τις αθλητικές δραστηριότητες.
     * @return Οι αθλητικές δραστηριότητες
     */
    public HashMap<String, Exercise> findAll() {
        HashMap<String, Exercise> result = new HashMap<String, Exercise>();
        result.putAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει μια αθλητική δραστηριότητα.
     * @param id Το όνομα της αθλητικής δραστηριότητας
     * @param entity Η αθλητική δραστηριότητα
     */
    public void save(String id,Exercise entity) { entities.put(id,entity); }

    /**
     * Βρίσκει μια αθλητική δραστηριότητα με βάση το όνομά της.
     * @param id Το όνομα της αθλητικής δραστηριότητας
     * @return Η αθλητική δραστηριότητα που βρέθηκε ή null
     */
    public Exercise find(String id)
    {
        for(HashMap.Entry<String, Exercise> m : entities.entrySet()) {
            if (m.getKey().equalsIgnoreCase(id)) {
                return (Exercise) m.getValue();
            }
        }
        return null;
    }

}
