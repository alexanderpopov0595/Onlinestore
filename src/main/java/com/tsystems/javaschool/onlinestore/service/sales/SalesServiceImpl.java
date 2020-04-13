package com.tsystems.javaschool.onlinestore.service.sales;

import java.time.LocalDate;
import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.order.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.javaschool.onlinestore.dao.sales.SalesDao;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;

import com.tsystems.javaschool.onlinestore.domain.user.User;


@Service
@Transactional
public class SalesServiceImpl implements SalesService{

    @Autowired
    private SalesDao salesDao;

    public void addSales(Order order) {

        for(OrderDetails od: order.getOrderDetailsList()) {
            for(int i=0; i<od.getQuantity();i++){
                Sales sales=new Sales();
                sales.setUser(order.getUser());
                sales.setOrder(order);
                sales.setOrderDetails(od);
                sales.setProduct(od.getProduct());
                sales.setRevenue(od.getProduct().getPrice());
                sales.setDate(LocalDate.now().plusDays(1));
                salesDao.addSales(sales);
            }
        }

    }

    @Override
    public void restoreSales(List<OrderDetails> orderDetailsList) {
        for(OrderDetails od: orderDetailsList) {
            salesDao.restoreSales(od.getId());
        }
    }


    public List<Product> getTopProductsList() {
        List<Product> productList=salesDao.getTopProductsList();
        for(Product p: productList) {
            p.getCategory();
        }
        return productList;
    }

    public List<User> getTopUserList() {
        return salesDao.getTopUserList();
    }

    public List<Sales> getWeekSales() {
        List<Sales> salesList=salesDao.getWeekSales();
        for(Sales s: salesList){
            s.getUser();
            s.getProduct();
        }
        return salesList;
    }

    public List<Sales> getMonthSales() {
        List<Sales> salesList=salesDao.getMonthSales();
        for(Sales s: salesList){
            s.getUser();
            s.getProduct();
        }
        return salesList;
    }



}