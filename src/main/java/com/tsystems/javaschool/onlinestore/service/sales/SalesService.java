package com.tsystems.javaschool.onlinestore.service.sales;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.order.Sales;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.User;

public interface SalesService {

    public void addSales(Order order);

    public void restoreSales(List<OrderDetails> orderDetailsList);



    public List<Product> getTopProductsList();

    public List<User> getTopUserList();

    public List<Sales> getWeekSales();

    public List<Sales> getMonthSales();
}