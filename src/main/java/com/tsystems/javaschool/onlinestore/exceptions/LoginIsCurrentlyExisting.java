package com.tsystems.javaschool.onlinestore.exceptions;

import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Exception throws when trying to add user with already existing account
 */
public class LoginIsCurrentlyExisting extends RuntimeException {
    private User user;

    public LoginIsCurrentlyExisting(User user){
        this.user=user;
    }
    /**
     * Method returns error message
     * @return error message
     */
    public String getMessage(){
        return "Login is currently existing";
    }

    public User getUser(){
        return user;
    }

}
