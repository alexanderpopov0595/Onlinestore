package com.tsystems.javaschool.onlinestore.service.order;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.order.Order;

/**
 * Inteface provides methods to work with orders
 */
public interface OrderService {

    /**
     * Method adds order to database
     * @param order
     * @return order id
     */
    public long addOrder(Order order);

    /**
     * Method updates order
     * @param order
     */
    public void updateOrder(Order order);

    /**
     * Method returns order by id
     * @param id
     * @param login
     * @param isRoleEmployee
     * @return
     */
    public Order selectOrder(long id, String login, boolean isRoleEmployee);


    /**
     * Method returns all orders
     * @return order list
     */
    public List<Order> selectOrderList();

    /**
     * Method returns all orders by user login
     * @param login
     * @return
     */
    public List<Order> selectOrderList( String login);

    /**
     * Method returns all orders by user id
     * @param id
     * @return
     */
    public List<Order> selectOrderList(long id);

    /**
     * Method returns orders by order status
     * @param OrderStatusCode
     * @return
     */
    public List<Order> selectOrderListByOrderStatus(String OrderStatusCode);

    /**
     * Method returns orders by user id and orders status
     * @param login
     * @param OrderStatusCode
     * @return
     */
    public List<Order> selectOrderListByOrderStatus(String login, String OrderStatusCode);

    /**
     * Method deletes order by id
     * @param id
     */
    public void deleteOrder(long id, String login, boolean isRoleEmployee);
}