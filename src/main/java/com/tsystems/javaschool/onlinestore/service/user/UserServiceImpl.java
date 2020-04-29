package com.tsystems.javaschool.onlinestore.service.user;

import javax.transaction.Transactional;
import com.tsystems.javaschool.onlinestore.enums.Status;
import com.tsystems.javaschool.onlinestore.exceptions.LoginIsCurrentlyExisting;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.tsystems.javaschool.onlinestore.dao.user.UserDao;
import com.tsystems.javaschool.onlinestore.domain.user.Address;
import com.tsystems.javaschool.onlinestore.domain.user.User;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger= Logger.getLogger(UserServiceImpl.class);
    /**
     * Injected user dao
     */
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Method sets user to addresses and adds user to database
     * If login is already existing - throws exeption
     *
     * @param user
     * @return created user id
     */
    @Transactional
    public long addUser(User user) {
        user.setAddresses();
        try {
            userDao.addUser(user);
        } catch (DataIntegrityViolationException ex) {
            throw new LoginIsCurrentlyExisting(user);
        }
        return user.getId();

    }

    /**
     * Method sets user to addresses and updates user in database     *
     *
     * @param user
     */
    @Transactional
    public void updateUser(User user) {
        User original = userDao.selectUser(user.getId());
        if (!original.getAddressList().isEmpty()) {
            for (Address a : original.getAddressList()) {
                if (!user.getAddressList().contains(a)) {
                    a.setStatus(Status.DELETED.toString());
                    user.getAddressList().add(a);

                }
            }
        }
        user.setAddresses();
        userDao.updateUser(user);
    }

    /**
     * Method gets user by id from database     *
     *
     * @param id
     * @return user
     */
    @Transactional
    public User selectUser(long id) {
        return userDao.selectUser(id);
    }

    /**
     * Method gets user by login from database
     * Then method initialize user addresses     *
     *
     * @param login
     * @return user
     */

    public User selectUser(String login) {
       User user = selectByLogin(login);
        user.setAddressList(selectAddressList(user.getId()));
        return user;
    }

    /**
     * Method loads active user by login
     *
     * @param login
     * @return user
     */
    @Transactional
    public User selectByLogin(String login) {
        return userDao.selectUser(login);
    }

    /**
     * Method returns only active user's addresses by user id
     *
     * @param id
     * @return address list
     */
    @Transactional
    public List<Address> selectAddressList(long id) {
        return userDao.selectAddressList(id);
    }

    /**
     * Method removes user from database
     * If user has some orders - method sets user status to DELETED and updates user in database
     * Else method deletes user addresses by its id and deletes user     *
     *
     * @param user
     */
    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user.getId());
        userDao.deleteAddresses(user.getId());
    }
}
