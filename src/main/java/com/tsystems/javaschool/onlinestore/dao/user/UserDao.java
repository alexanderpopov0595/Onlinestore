package com.tsystems.javaschool.onlinestore.dao.user;

import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Interface provides methods to work with user in database
 */
public interface UserDao {

    /**
     *  Method adds user to database     *
     * @param user
     */
    public void addUser(User user);

    /**
     * Method updates user and user addresses in database
     * @param user
     */
    public void updateUser(User user);

    /**
     * Method returns user by id
     * @param id
     * @return
     */
    public User selectUser(long id);

    /**
     * Method returns user by login
     * @param login
     * @return
     */
    public User selectUser(String login);

    /**
     * Method deletes user and addresse by user id
     * @param id
     */
    public void deleteUser(long id);






}
