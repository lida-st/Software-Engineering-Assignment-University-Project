package com.example.sugarsweet.memorydao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.dao.UsersDAO;
import com.example.sugarsweet.domain.User;

import java.util.HashMap;

public class UsersDAOMemory implements UsersDAO {

    protected static HashMap<String, User> entities = new HashMap <String,User>();

    /**
     * Διαγράφει έναν χρήστη.
     * @param entity Ο κωδικός του χρήστη
     */
    public void delete(String entity) {
        entities.remove(entity);
    }

    /**
     * Επιστρέφει όλους τους χρήστες.
     * @return Οι χρήστες
     */
    public HashMap<String, User> findAll() {
        HashMap<String, User> result = new HashMap<String, User>();
        result.putAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει έναν χρήστη.
     * @param id Ο κωδικός του χρήστη
     * @param entity Ο χρήστης
     */
    public void save(String id, User entity) {
        entities.put(id, entity);
    }

    /**
     * Βρίσκει έναν χρήστη με βάση τον κωδικό του.
     * @param id Ο κωδικός του χρήστη
     * @return Ο χρήστης που βρέθηκε ή null
     */
    public User find(String id)
    {
        for(HashMap.Entry m : entities.entrySet()) {
            if (m.getKey().equals(id)) {
                return (User) m.getValue();
            }
        }

        return null;
    }

}
