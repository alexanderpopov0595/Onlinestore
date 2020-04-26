package com.tsystems.javaschool.onlinestore.exceptions;

public class ExistException extends RuntimeException {

    public String getMessage(){
        return "Content not found";
    }
}
