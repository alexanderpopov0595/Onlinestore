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

    /**
     * Injected deposit dao and user dao
     */
    private DepositDao depositDao;

    private UserDao userDao;

    @Autowired
    public DepositServiceImpl(DepositDao depositDao, UserDao userDao){
        this.depositDao=depositDao;
        this.userDao=userDao;
    }

    /**
     * Method adds deposit to database
     * If deposit is already existing - throws exception
     * @param deposit with information about deposit number and password
     * @param login of owner
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
     * Method updates deposit in database
     * @param deposit with information about deposit number, password and owner
     */
    @Override
    public void updateDeposit(Deposit deposit) {
        depositDao.updateDeposit(deposit);

    }

    /**
     * Method selects deposit by user's login
     * If deposit is not found - throws exception
     * @param login
     * @return deposit
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
     * Method gets deposit balance before update, sums it to added balance value and updates deposit balance
     * @param deposit
     */
    @Override
    public void updateDepositBalance(Deposit deposit) {
        Deposit original=depositDao.selectDeposit(deposit.getId());
        deposit.setBalance(original.getBalance()+deposit.getBalance());
        depositDao.updateDepositBalance(deposit);

    }

    /**
     * Method deletes deposit from database
     *@param deposit with information about deposit number, password and owner
     */
    @Override
    public void deleteDeposit(Deposit deposit) {
        depositDao.deleteDeposit(deposit.getId());
    }
}