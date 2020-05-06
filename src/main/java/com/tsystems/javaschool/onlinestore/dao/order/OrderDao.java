package com.tsystems.javaschool.onlinestore.dao.order;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.enums.OrderStatus;

/**
 * Method provides methods to work with orders in database
 */
public interface OrderDao {

    /**
     * Method adds order to database
     * @param order
     */
    void addOrder(Order order);

    /**
     * Method updates order
     * @param order
     */
    void updateOrder(Order order);


    /**
     * Method returns order by id
     * @param id
     * @return order
     */
    Order selectOrder(long id);


    /**
     * Method returns all orders
     * @return order list
     */
    List<Order> selectOrderList();

    /**
     * Method returns all orders by user login
     * @param login
     * @return order list
     */
    List<Order> selectOrderList(String login);

    /**
     * Method returns all orders by user id
     * @param userID
     * @return order list
     */
    List<Order> selectOrderList(long userID);

    /**
     * Method returns order list by order status
     * @param orderStatus
     * @return order list
     */
    List<Order> selectOrderListByOrderStatus(OrderStatus orderStatus);

    /**
     * Method returns order list by order status and user login     *
     * @param login
     * @param orderStatus
     * @return order list
     */
    List<Order> selectOrderListByOrderStatus(String login, OrderStatus orderStatus);

    /**
     * Method returns product quantity
     * @param id
     * @return product quantity
     */
    int selectProductQuantity(long id);

    /**
     * Method decrease/increase product quantity from order
     * @param id
     * @param quantity
     */
    void changeProductQuantityFromOrder(long id, int quantity);

    /**
     * Method deletes order
     * @param id
     */
    void deleteOrder(long id);

    /**
     * Method deletes order details
     * @param id
     */
    void deleteOrderDetails(long id);

}