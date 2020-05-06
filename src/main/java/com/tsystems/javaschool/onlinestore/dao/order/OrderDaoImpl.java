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

    /**
     * Method adds order to database
     * @param order
     */
    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    /**
     * Method updates order
     * @param order
     */
    public void updateOrder(Order order) {
        entityManager.merge(order);
    }

    /**
     * Method returns order by id
     * @param id
     * @return order
     */
    public Order selectOrder(long id) {
        return entityManager.find(Order.class, id);
    }

    /**
     * Method returns all orders
     * @return order list
     */
    public List<Order> selectOrderList() {
        return  entityManager.createQuery("SELECT o FROM Order o")
                .getResultList();
    }
    /**
     * Method returns all orders by user id
     * @param userID
     * @return order list
     */
    public List<Order> selectOrderList(long userID) {
        return  entityManager.createQuery("SELECT o FROM Order o WHERE o.user.id=:id")
                .setParameter("id", userID)
                .getResultList();
    }

    /**
     * Method returns all orders by user login
     * @param login
     * @return order list
     */
    public List<Order> selectOrderList(String login) {
        return  entityManager.createQuery("SELECT o FROM Order o JOIN User u ON o.user.id=u.id WHERE u.login=:login")
                .setParameter("login", login)
                .getResultList();
    }
    /**
     * Method returns order list by order status
     * @param orderStatus
     * @return order list
     */
    public List<Order> selectOrderListByOrderStatus(OrderStatus orderStatus) {
        return  entityManager.createQuery("SELECT o FROM Order o WHERE o.orderStatus=:orderStatus")
                .setParameter("orderStatus", orderStatus)
                .getResultList();
    }
    /**
     * Method returns order list by order status and user login     *
     * @param login
     * @param orderStatus
     * @return order list
     */
    public List<Order> selectOrderListByOrderStatus(String login, OrderStatus orderStatus) {
        return entityManager.createQuery("SELECT o FROM Order o JOIN User u ON o.user.id=u.id WHERE u.login=:login AND  o.orderStatus=:orderStatus")
                .setParameter("login", login).setParameter("orderStatus", orderStatus)
                .getResultList();
    }

    /**
     * Method returns product quantity
     * @param id
     * @return product quantity
     */
    public int selectProductQuantity(long id) {
        return (int) entityManager.createQuery("SELECT p.quantity FROM Product p WHERE p.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * Method decrease/increase product quantity from order
     * @param id
     * @param quantity
     */
    public void changeProductQuantityFromOrder(long id, int quantity) {
        entityManager.createQuery("UPDATE Product p SET p.quantity=:quantity WHERE p.id=:id" )
                .setParameter("quantity", quantity).setParameter("id", id)
                .executeUpdate();
    }
    /**
     * Method deletes order
     * @param id
     */
    public void deleteOrder(long id) {
        entityManager.createQuery("DELETE Order o WHERE o.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
    /**
     * Method deletes order details
     * @param id
     */
    public void deleteOrderDetails(long id) {
        entityManager.createQuery("DELETE OrderDetails od WHERE od.id=:id")
                .setParameter("id",id)
                .executeUpdate();
    }

}