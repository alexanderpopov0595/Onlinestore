package com.tsystems.javaschool.onlinestore.dao.order;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.enums.OrderStatus;

/**
 * JPA implementation of OrderDao
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    /**
     * JPA entity manager , provided by Spring
     */
    @PersistenceContext
    private EntityManager entityManager;

    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    public void updateOrder(Order order) {
        entityManager.merge(order);
    }

    public Order selectOrder(long id) {
        return entityManager.find(Order.class, id);
    }

    public List<Order> selectOrderList() {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o")
                .getResultList();
    }
    public List<Order> selectOrderList(long id_user) {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o WHERE o.user.id=:id")
                .setParameter("id", id_user)
                .getResultList();
    }
    public List<Order> selectOrderList(String login) {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o JOIN User u ON o.user.id=u.id WHERE u.login=:login")
                .setParameter("login", login)
                .getResultList();
    }
    public List<Order> selectOrderListByOrderStatus(OrderStatus orderStatus) {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o WHERE o.orderStatus=:orderStatus")
                .setParameter("orderStatus", orderStatus)
                .getResultList();
    }
    public List<Order> selectOrderListByOrderStatus(String login, OrderStatus orderStatus) {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o JOIN User u ON o.user.id=u.id WHERE u.login=:login AND  o.orderStatus=:orderStatus")
                .setParameter("login", login).setParameter("orderStatus", orderStatus)
                .getResultList();
    }

    public int selectProductQuantity(long id) {
        return (int) entityManager.createQuery("SELECT p.quantity FROM Product p WHERE p.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public void changeProductQuantityFromOrder(long id, int quantity) {
        entityManager.createQuery("UPDATE Product p SET p.quantity=:quantity WHERE p.id=:id" )
                .setParameter("quantity", quantity).setParameter("id", id)
                .executeUpdate();
    }
    public void deleteOrder(long id) {
        entityManager.createQuery("DELETE Order o WHERE o.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
    public void deleteOrderDetails(long id) {
        entityManager.createQuery("DELETE OrderDetails od WHERE od.id=:id")
                .setParameter("id",id)
                .executeUpdate();
    }

}