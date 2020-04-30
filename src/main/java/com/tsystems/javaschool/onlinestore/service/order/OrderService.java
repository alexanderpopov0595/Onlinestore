package com.tsystems.javaschool.onlinestore.service.order;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.order.Order;

/**
 * Interface provides methods to work with orders
 */
public interface OrderService {

    /**
     * Method adds order to database
     * @param order
     * @return order id
     */
    long addOrder(Order order);

    /**
     * Method updates order
     * @param order
     */
    void updateOrder(Order order);

    /**
     * Method returns order by id
     * @param id
     * @param login
     * @param isRoleEmployee
     * @return
     */
     Order selectOrder(long id, String login, boolean isRoleEmployee);


    /**
     * Method returns all orders
     * @return order list
     */
     List<Order> selectOrderList();

    /**
     * Method returns all orders by user login
     * @param login
     * @return
     */
   List<Order> selectOrderList( String login);

    /**
     * Method returns all orders by user id
     * @param id
     * @return
     */
     List<Order> selectOrderList(long id);

    /**
     * Method returns orders by order status
     * @param orderStatusCode
     * @return
     */
 List<Order> selectOrderListByOrderStatus(String orderStatusCode);

    /**
     * Method returns orders by user id and orders status
     * @param login
     * @param orderStatusCode
     * @return
     */
    List<Order> selectOrderListByOrderStatus(String login, String orderStatusCode);

    /**
     * Method deletes order by id
     * @param id
     */
    void deleteOrder(long id, String login, boolean isRoleEmployee);
}