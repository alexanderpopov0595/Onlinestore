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

    public void addUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }

    public User selectUser(long id) {
        return entityManager.find(User.class, id);
    }

    public User selectUser(String login) {
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login AND u.status=:status")
                .setParameter("login", login)
                .setParameter("status", Status.ACTIVE)
                .getSingleResult();

    }
    public List<Address> selectAddressList(long id){
        return (List<Address>) entityManager.createQuery("SELECT a FROM Address a WHERE a.status=:status AND a.user.id=:id")
                .setParameter("status", Status.ACTIVE)
                .setParameter("id", id)
                .getResultList();
    }

    public void deleteUser(long id) {
        entityManager.createQuery("UPDATE User u SET u.status=:status WHERE u.id=:id")
                .setParameter("status", Status.DELETED)
                .setParameter("id", id)
                .executeUpdate();

    }
    public void deleteAddresses(long id){
        entityManager.createQuery("UPDATE Address a SET a.status=:status WHERE a.user.id=:id")
                .setParameter("status", Status.DELETED)
                .setParameter("id", id)
                .executeUpdate();
    }

}
