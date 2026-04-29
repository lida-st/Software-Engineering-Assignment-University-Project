package com.example.sugarsweet.dao;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import com.example.sugarsweet.domain.Ingredient;

import java.util.HashMap;
public interface IngredientsDAO {

    /**
     * Διαγράφει ένα συστατικό γεύματος.
     * @param entity Το όνομα του συστατικού
     */
    void delete(String entity);

    /**
     * Επιστρέφει όλα τα συστατικά γευμάτων.
     * @return Τα συστατικά γευμάτων
     */
    HashMap <String,Ingredient> findAll();

    /**
     * Αποθηκεύει ένα συστατικό γεύματος.
     * @param id το όνομα του συστατικού γεύματος
     * @param entity Το συστατικό γεύματος
     */
    void save(String id, Ingredient entity);

    /**
     * Βρίσκει ένα συστατικό γεύματος με βάση το όνομά του.
     * @param IngredientId Το όνομα του συστατικού γεύματος
     * @return Το συστατικό που βρέθηκε ή null
     */
    Ingredient find(String IngredientId);

}
