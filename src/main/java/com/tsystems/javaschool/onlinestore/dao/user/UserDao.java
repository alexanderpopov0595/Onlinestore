package com.tsystems.javaschool.onlinestore.dao.user;

import com.tsystems.javaschool.onlinestore.domain.user.Address;
import com.tsystems.javaschool.onlinestore.domain.user.User;
import java.util.List;

/**
 * Interface provides methods to work with user in database
 */
public interface UserDao {

    /**
     * Method adds user to database
     * @param user
     */
    void addUser(User user);

    /**
     * Method updates user and user addresses in database
     * @param user
     */
    void updateUser(User user);

    /**
     * Method returns user by id
     * @param id
     * @return user
     */
    User selectUser(long id);

    /**
     * Method returns user by login with active status
     * @param login
     * @return user
     */
     User selectUser(String login);

    /**
     * Method returns addresses with active status by user id
     * @param id
     * @return address list
     */
     List<Address> selectAddressList(long id);

    /**
     * Method changes user status to DELETED by user id
     * @param id
     */
     void deleteUser(long id);

    /**
     * Method changes addresses status to DELETED by user id
     * @param id
     */
     void deleteAddresses(long id);

}
