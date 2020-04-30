package com.tsystems.javaschool.onlinestore.dao.deposit;

import com.tsystems.javaschool.onlinestore.domain.user.Deposit;

/**
 * Interface provides methods to work with user deposit in database
 */
public interface DepositDao {


    /**
     * Method adds deposit to database
     * @param deposit with information about deposit number and password
     */
     void addDeposit(Deposit deposit);

    /**
     * Method updates deposit in database
     * @param deposit with information about deposit number, password and owner
     */
     void updateDeposit(Deposit deposit);

    /**
     * Method returns deposit by id from database
     * @param  id of deposit
     * @return deposit object
     */
   Deposit selectDeposit(long id);
    /**
     * Method returns deposit by owner id from database
     * @param  id of user
     * @return deposit object
     */
     Deposit selectUserDeposit(long id);

    /**
     * Method updates deposit balance
     * @param deposit
     */
     void updateDepositBalance(Deposit deposit);


    /**
     * Method deletes deposit from database
     *@param  id of deposit
     */
   void deleteDeposit(long id);

}