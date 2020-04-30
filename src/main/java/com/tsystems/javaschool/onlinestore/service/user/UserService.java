package com.tsystems.javaschool.onlinestore.service.user;

import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Interface provides methods to work with users
 */
public interface UserService {

    /**
     * Method adds user to database
     * @param user
     */
    long addUser(User user);

    /**
     * Method updates user in database
     * @param user
     */
    void updateUser(User user);

    /**
     * Method returns user by login
     * @param login
     * @return user
     */
     User selectUser(String login);

    /**
     * Method deletes user from database
     * @param user
     */
     void deleteUser(User user);
}
