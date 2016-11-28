package com.mulcahy.domain;

import com.mulcahy.model.User;

/**
 * Created by Chris on 11/17/2016.
 */
public class Validate {


    public static boolean validateUser(User u){
        if(u==null){
            return false;
        }
        return true;
    }

}
