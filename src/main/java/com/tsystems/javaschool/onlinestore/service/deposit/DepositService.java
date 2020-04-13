package com.tsystems.javaschool.onlinestore.service.deposit;

import com.tsystems.javaschool.onlinestore.domain.user.Deposit;

/**
 * Interface provides methods to work with user deposit
 *
 */
public interface DepositService {

    /**
     * Method adds deposit to database
     * @param deposit with information about deposit number and password
     * @param login of owner
     */
    public void addDeposit(Deposit deposit, String login);

    /**
     * Method updates deposit in database
     * @param deposit with information about deposit number, password and owner
     */
    public void updateDeposit(Deposit deposit);


    /**
     * Method returns deposit by user's login
     * @param owner login
     * @return deposit object
     */
    public Deposit selectDeposit(String login);


    /**
     * Method updates deposit balance
     * @param deposit
     */
    public void updateDepositBalance(Deposit deposit);


    /**
     * Method deletes deposit from database
     *@param deposit with information about deposit number, password and owner
     */
    public void deleteDeposit(Deposit deposit);

}