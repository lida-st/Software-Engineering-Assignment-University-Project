package com.example.sugarsweet.util;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

public class Tuple2<String,Ingredient,Integer> {

    public final String x;
    public final Ingredient y;
    public final Integer z;

    public Tuple2(String x, Ingredient y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}

