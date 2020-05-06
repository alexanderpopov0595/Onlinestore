package com.tsystems.javaschool.onlinestore.dao.user;

import com.tsystems.javaschool.onlinestore.domain.user.Address;
import com.tsystems.javaschool.onlinestore.domain.user.User;
import com.tsystems.javaschool.onlinestore.enums.Status;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JPA implementation of UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * JPA entity manager , provided by Spring
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method adds user to database
     * @param user
     */
    public void addUser(User user) {
        entityManager.persist(user);
    }

    /**
     * Method updates user and user addresses in database
     * @param user
     */
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    /**
     * Method returns user by id
     * @param id
     * @return user
     */
    public User selectUser(long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Method returns user by login with active status
     * @param login
     * @return user
     */
    public User selectUser(String login) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login AND u.status=:status")
                .setParameter("login", login)
                .setParameter("status", Status.ACTIVE)
                .getSingleResult();
    }
    /**
     * Method returns addresses with active status by user id
     * @param id
     * @return address list
     */
    public List<Address> selectAddressList(long id){
        return entityManager.createQuery("SELECT a FROM Address a WHERE a.status=:status AND a.user.id=:id")
                .setParameter("status", Status.ACTIVE)
                .setParameter("id", id)
                .getResultList();
    }

    /**
     * Method changes user status to DELETED by user id
     * @param id
     */
    public void deleteUser(long id) {
        entityManager.createQuery("UPDATE User u SET u.status=:status WHERE u.id=:id")
                .setParameter("status", Status.DELETED)
                .setParameter("id", id)
                .executeUpdate();

    }
    /**
     * Method changes addresses status to DELETED by user id
     * @param id
     */
    public void deleteAddresses(long id){
        entityManager.createQuery("UPDATE Address a SET a.status=:status WHERE a.user.id=:id")
                .setParameter("status", Status.DELETED)
                .setParameter("id", id)
                .executeUpdate();
    }

}
