package com.tsystems.javaschool.onlinestore.dao.deposit;

import com.tsystems.javaschool.onlinestore.domain.user.Deposit;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA implementation of DepositDao
 */
@Repository
public class DepositDaoImpl implements DepositDao {

    /**
     * JPA entity manager , provided by Spring
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addDeposit(Deposit deposit) {
        entityManager.persist(deposit);

    }

    @Override
    public void updateDeposit(Deposit deposit) {
        entityManager.merge(deposit);

    }

    @Override
    public Deposit selectDeposit(long id) {
        return entityManager.find(Deposit.class, id);
    }

    @Override
    public Deposit selectUserDeposit(long id) {

        return (Deposit) entityManager.createQuery("SELECT d FROM Deposit d WHERE d.user.id=:id").setParameter("id", id).getSingleResult();
    }

    @Override
    public void updateDepositBalance(Deposit deposit) {
        entityManager.createQuery("UPDATE Deposit d SET d.balance=:balance WHERE d.id=:id").setParameter("balance", deposit.getBalance()).setParameter("id", deposit.getId()).executeUpdate();

    }

    @Override
    public void deleteDeposit(long id) {
        entityManager.createQuery("DELETE FROM Deposit d WHERE d.id=:id").setParameter("id",  id).executeUpdate();

    }


}