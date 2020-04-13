package com.tsystems.javaschool.onlinestore.dao.user;

import com.tsystems.javaschool.onlinestore.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * JPA implementation of UserDao
 */
@Repository
public class UserDaoImpl implements UserDao {

    /*
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
        return (User) entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login).getSingleResult();
    }

    public void deleteUser(long id) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id=:id").setParameter("id", id).executeUpdate();
        entityManager.createQuery("DELETE FROM Address a WHERE a.user.id=:id").setParameter("id", id).executeUpdate();
    }


}
