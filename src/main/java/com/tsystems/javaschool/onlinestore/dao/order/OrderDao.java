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
    void addOrder(Order order);

    /*
     * Method updates order
     */
   void updateOrder(Order oder);


    /*
     * Method returns order
     */
     Order selectOrder(long id);


    /*
     * Methods returns all orders
     */
   List<Order> selectOrderList();

    List<Order> selectOrderList(String login);

    List<Order> selectOrderList(long userID);

    /*
     * Method returns order list by order status
     */
    List<Order> selectOrderListByOrderStatus(OrderStatus orderStatus);

     List<Order> selectOrderListByOrderStatus(String login,OrderStatus orderStatus);

    /*
     * Method checks product quantity
     */
    int selectProductQuantity(long id);

    /*
     * Method decrease/increase product quantity from order
     */
     void changeProductQuantityFromOrder(long id, int quantity);

    /*
     * Method deletes order
     */
     void deleteOrder(long id);

    /*
     * Method deletes order_details
     */
 void deleteOrderDetails(long id);

}