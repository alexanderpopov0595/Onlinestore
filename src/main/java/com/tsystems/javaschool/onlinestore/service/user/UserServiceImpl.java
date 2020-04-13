package com.tsystems.javaschool.onlinestore.service.user;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import com.tsystems.javaschool.onlinestore.exceptions.LoginIsCurrentlyExisting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.tsystems.javaschool.onlinestore.dao.user.UserDao;
import com.tsystems.javaschool.onlinestore.domain.user.Address;
import com.tsystems.javaschool.onlinestore.domain.user.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * Injected user dao
     */
    @Autowired
    private UserDao userDao;

    /**
     * Method sets user to addresses and adds user to database
     * If login is already existing - throws exeption
     * @param user
     */
    public void addUser(User user) {
        user.setAddresses();

        try {
            userDao.addUser(user);
        } catch (DataIntegrityViolationException ex) {
            throw new LoginIsCurrentlyExisting(user);
        }

    }

    /**
     * Method sets user to addresses and updates user in database     *
     * @param user
     */
    public void updateUser(User user) {
        user.setAddresses();
        userDao.updateUser(user);
    }

    /**
     * Method gets user by id from database
     *
     * @param  id
     * @return user
     */
    public User selectUser(long id) {
        return userDao.selectUser(id);
    }

    /**
     * Method gets user by login from database
     * Then method initialize user addresses
     *
     * @param  login
     * @return user
     */
    public User selectUser(String login) {
        User user = userDao.selectUser(login);
        user.getAddressList().size();
        return user;
    }

    /**
     * Method removes user from database
     * If user has some orders - method sets user status to DELETED and updates user in database
     * Else method deletes user addresses by its id and deletes user
     *
     * @param user
     */
    public void deleteUser(User user) {
        //if(dao.selectUserOrders(user.getId())!=0){
        //user.setStatus(Status.DELETED);;
        //user.setAddresses();
        //userDao.updateUser(user);
        //else{
        userDao.deleteUser(user.getId());
    }
}
