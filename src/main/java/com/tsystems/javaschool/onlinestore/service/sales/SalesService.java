package com.tsystems.javaschool.onlinestore.service.sales;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.order.Sales;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Interface provides methods to work with sales in database
 */
public interface SalesService {

    /**
     * Method adds order to sales table
     * @param order
     */
     void addSales(Order order);

    /**
     * Method removes order details from sales table
     * @param orderDetailsList
     */
     void restoreSales(List<OrderDetails> orderDetailsList);

    /**
     * Method return top-10 products
     * @return products list
     */
     List<Product> getTopProductsList();

    /**
     * Method returns top-10 users
     * @return users list
     */
    List<User> getTopUserList();

    /**
     * Method returns sales per week
     * @return sales list
     */
    List<Sales> getWeekSales();

    /**
     * Method returns sales per month
     * @return sales list
     */
    List<Sales> getMonthSales();
}