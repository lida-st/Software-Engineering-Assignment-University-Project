package com.example.sugarsweet.dao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.domain.User;

import java.util.HashMap;

public interface UsersDAO {

    /**
     * Διαγράφει έναν χρήστη.
     * @param entity Ο κωδικός του χρήστη
     */
    void delete(String entity);

    /**
     * Επιστρέφει όλους τους χρήστες.
     * @return Οι χρήστες
     */
    HashMap <String,User> findAll();

    /**
     * Αποθηκεύει έναν χρήστη.
     * @param id Ο κωδικός του χρήστη
     * @param entity Ο χρήστης
     */
    void save(String id,User entity);

    /**
     * Βρίσκει έναν χρήστη με βάση τον κωδικό του.
     * @param UserId Ο κωδικός του χρήστη
     * @return Ο χρήστης που βρέθηκε ή null
     */
    User find(String UserId);

}
