package com.tsystems.javaschool.onlinestore.dao.sales;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.order.Sales;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Interface provides methods to work with sales in database
 *
 */
public interface SalesDao {

    /**
     * Method adds sale to database
     * @param sales with information about purchased order
     */
     void addSales(Sales sales);

    /**
     * Method deletes sales by order details id
     * @param id of order details
     */
     void restoreSales(long id);

    /**
     * Method returms top-10 products
     * @return product list
     */
     List<Product> getTopProductsList();

    /**
     * Method returns top-10 users
     * @return user list
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