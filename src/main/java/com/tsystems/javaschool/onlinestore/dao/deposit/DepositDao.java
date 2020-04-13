package com.tsystems.javaschool.onlinestore.dao.deposit;

import com.tsystems.javaschool.onlinestore.domain.user.Deposit;

/**
 * Inteface prodives methods to work with user deposit in database
 */
public interface DepositDao {


    /**
     * Method adds deposit to database
     * @param deposit with information about deposit number and password
     */
    public void addDeposit(Deposit deposit);

    /**
     * Method updates deposit in database
     * @param deposit with information about deposit number, password and owner
     */
    public void updateDeposit(Deposit deposit);

    /**
     * Method returns deposit by id from database
     * @param deposit id
     * @return deposit object
     */
    public Deposit selectDeposit(long id);
    /**
     * Method returns deposit by owner id from database
     * @param user id
     * @return deposit object
     */
    public Deposit selectUserDeposit(long id);

    /**
     * Method updates deposit balance
     * @param deposit
     */
    public void updateDepositBalance(Deposit deposit);


    /**
     * Method deletes deposit from database
     *@param deposit id
     */
    public void deleteDeposit(long id);

}