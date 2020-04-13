package com.tsystems.javaschool.onlinestore.service.deposit;

import com.tsystems.javaschool.onlinestore.dao.deposit.DepositDao;
import com.tsystems.javaschool.onlinestore.dao.user.UserDao;
import com.tsystems.javaschool.onlinestore.domain.user.Deposit;
import com.tsystems.javaschool.onlinestore.domain.user.User;
import com.tsystems.javaschool.onlinestore.exceptions.DepositAlreadyExistingException;
import com.tsystems.javaschool.onlinestore.exceptions.NoDepositExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositDao depositDao;

    @Autowired
    private UserDao userDao;

    /**
     * Method gets user by user login, sets user to deposit and adds deligates deposit to depositDao add method
     */
    @Override
    public void addDeposit(Deposit deposit, String login) {
        User user=userDao.selectUser(login);
        deposit.setUser(user);
        try {
            depositDao.addDeposit(deposit);
        }
        catch(DataIntegrityViolationException ex) {
            throw new DepositAlreadyExistingException();
        }
    }

    /**
     * Method deligates deposit to depositDao update method
     */
    @Override
    public void updateDeposit(Deposit deposit) {
        depositDao.updateDeposit(deposit);

    }

    /**
     * Method gets user by use login, gets user id and deligates to depositDao select method
     */
    @Override
    public Deposit selectDeposit(String login) {
        Deposit deposit;
        try {
            deposit=depositDao.selectUserDeposit(userDao.selectUser(login).getId());
        }
        catch(EmptyResultDataAccessException ex) {
            throw new NoDepositExistingException();
        }
        return deposit;
    }

    /**
     * Method gets deposit balance before update, sums it to added balance value and deligates to depositDao update balance method
     */
    @Override
    public void updateDepositBalance(Deposit deposit) {
        Deposit original=depositDao.selectDeposit(deposit.getId());
        deposit.setBalance(original.getBalance()+deposit.getBalance());
        depositDao.updateDepositBalance(deposit);

    }

    /**
     * Method deligates to depositDao delete method
     */
    @Override
    public void deleteDeposit(Deposit deposit) {
        depositDao.deleteDeposit(deposit.getId());

    }



}