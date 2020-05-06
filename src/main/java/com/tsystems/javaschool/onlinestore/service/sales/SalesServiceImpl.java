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
public class SalesServiceImpl implements SalesService {

    /**
     * Injected salesDao
     */
    private SalesDao salesDao;

    @Autowired
    public SalesServiceImpl(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    /**
     * Method creates sales for every product details from order and add sales to database
     *
     * @param order
     */
    public void addSales(Order order) {
        for (OrderDetails od : order.getOrderDetailsList()) {
            for (int i = 0; i < od.getQuantity(); i++) {
                Sales sales = new Sales();
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
    /**
     * Method removes order details from sales table
     * @param orderDetailsList
     */
    @Override
    public void restoreSales(List<OrderDetails> orderDetailsList) {
        for (OrderDetails od : orderDetailsList) {
            salesDao.restoreSales(od.getId());
        }
    }

    /**
     * Method loads top product list and loads categories for every product
     *
     * @return product list
     */
    public List<Product> getTopProductsList() {
        List<Product> productList = salesDao.getTopProductsList();
        for (Product p : productList) {
            p.getCategory();
        }
        return productList;
    }

    /**
     * Method returns top-10 users
     * @return users list
     */
    public List<User> getTopUserList() {
        return salesDao.getTopUserList();
    }

    /**
     * Method loads week sales
     *
     * @return week sales list
     */
    public List<Sales> getWeekSales() {

        return salesDao.getWeekSales();
    }

    /**
     * Method loads month sales
     *
     * @return month sales list
     */
    public List<Sales> getMonthSales() {

        return salesDao.getMonthSales();
    }
}