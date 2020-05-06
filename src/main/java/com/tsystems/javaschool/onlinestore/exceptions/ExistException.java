package com.tsystems.javaschool.onlinestore.exceptions;

/**
 * Class represents exception thrown while trying access to not existing content
 */
public class ExistException extends RuntimeException {

    @Override
    public String getMessage(){
        return "Content not found";
    }
}
