package com.tsystems.javaschool.onlinestore.exceptions;

public class ExistException extends RuntimeException {

    @Override
    public String getMessage(){
        return "Content not found";
    }
}
