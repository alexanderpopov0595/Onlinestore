package com.tsystems.javaschool.onlinestore.dao.order;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.enums.OrderStatus;

/**
 * Method provides methods to work with orders in database
 */
public interface OrderDao {

    /*
     * Method adds order to database
     */
    public void addOrder(Order order);

    /*
     * Method updates order
     */
    public void updateOrder(Order oder);


    /*
     * Method returns order
     */
    public Order selectOrder(long id);


    /*
     * Methods returns all orders
     */
    public List<Order> selectOrderList();

    public List<Order> selectOrderList(String login);

    public List<Order> selectOrderList(long userID);

    /*
     * Method returns order list by order status
     */
    public List<Order> selectOrderListByOrderStatus(OrderStatus orderStatus);

    public List<Order> selectOrderListByOrderStatus(String login,OrderStatus orderStatus);

    /*
     * Method checks product quantity
     */
    public int selectProductQuantity(long id);

    /*
     * Method decrease/increase product quantity from order
     */
    public void changeProductQuantityFromOrder(long id, int quantity);

    /*
     * Method deletes order
     */
    public void deleteOrder(long id);

    /*
     * Method deletes order_details
     */
    public void deleteOrderDetails(long id);

}