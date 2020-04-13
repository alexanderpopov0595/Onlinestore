package com.tsystems.javaschool.onlinestore.dao.sales;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.order.Sales;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Inteface provides methods to work with sales in database
 *
 */
public interface SalesDao {

    /**
     * Method adds sale to database
     * @param sale with information about purchased order
     */
    public void addSales(Sales sales);

    /**
     * Method deletes sales by order details id
     * @param order details id
     */
    public void restoreSales(long id);



    public List<Product> getTopProductsList();

    public List<User> getTopUserList();

    public List<Sales> getWeekSales();

    public List<Sales> getMonthSales();


}