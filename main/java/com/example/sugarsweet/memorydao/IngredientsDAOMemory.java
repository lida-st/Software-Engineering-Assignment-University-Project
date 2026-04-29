package com.example.sugarsweet.memorydao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.dao.IngredientsDAO;
import com.example.sugarsweet.domain.Exercise;
import com.example.sugarsweet.domain.Ingredient;

import java.util.HashMap;

public class IngredientsDAOMemory implements IngredientsDAO {

    protected static HashMap<String, Ingredient> entities = new HashMap <String,Ingredient>();

    /**
     * Διαγράφει ένα συστατικό γεύματος.
     * @param entity Το όνομα του συστατικού
     */
    public void delete(String entity) {
        entities.remove(entity);
    }

    /**
     * Επιστρέφει όλα τα συστατικά γευμάτων.
     * @return Τα συστατικά
     */
    public HashMap<String, Ingredient> findAll() {
        HashMap<String, Ingredient> result = new HashMap<String, Ingredient>();
        result.putAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει ένα συστατικό γεύματος.
     * @param id Το όνομα του συστατικού γεύματος
     * @param entity Το συστατικό γεύματος
     */
    public void save(String id,Ingredient entity) { entities.put(id,entity); }

    /**
     * Βρίσκει ένα συστατικό γεύματος με βάση το όνομά του.
     * @param id Το όνομα του συστατικού γεύματος
     * @return Το συστατικό που βρέθηκε ή null
     */
    public Ingredient find(String id)
    {
        for(HashMap.Entry<String, Ingredient> m : entities.entrySet()) {
            if (m.getKey().equalsIgnoreCase(id)) {
                return (Ingredient) m.getValue();
            }
        }
        return null;
    }
}
