package com.tsystems.javaschool.onlinestore.service.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import com.tsystems.javaschool.onlinestore.exceptions.AccessDeniedException;
import com.tsystems.javaschool.onlinestore.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.javaschool.onlinestore.dao.deposit.DepositDao;
import com.tsystems.javaschool.onlinestore.dao.order.OrderDao;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.Deposit;
import com.tsystems.javaschool.onlinestore.enums.OrderStatus;
import com.tsystems.javaschool.onlinestore.enums.PaymentStatus;
import com.tsystems.javaschool.onlinestore.enums.PaymentType;
import com.tsystems.javaschool.onlinestore.exceptions.OutOfBalanceException;
import com.tsystems.javaschool.onlinestore.exceptions.OutOfQuantityException;
import com.tsystems.javaschool.onlinestore.service.sales.SalesService;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    private SalesService salesService;

    private MessageService messageService;

    private ProductDao productDao;

    private DepositDao depositDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, SalesService salesService, MessageService messageService, ProductDao productDao, DepositDao depositDao){
        this.orderDao=orderDao;
        this.salesService=salesService;
        this.messageService=messageService;
        this.productDao=productDao;
        this.depositDao=depositDao;
    }

    /**
     * Method compares the actual quantity of products in the database with the
     * quantity of products in the order. If actual quantity of product is smaller
     * than quantity in order - methods adds this product to unavailable product
     * map
     *
     * @param orderDetailsList
     * @return map with unavailable products
     */
    public Map<Product, Integer> getUnavailableProductMap(List<OrderDetails> orderDetailsList){
        Map<Product, Integer> unavailableProductMap=new HashMap<>();
        int quantity;
        int count;
        for (OrderDetails od : orderDetailsList) {
            od.setProduct(productDao.selectProduct(od.getProduct().getId()));
            quantity=od.getProduct().getQuantity();
            count = od.getQuantity();
            if(count > quantity) {
                unavailableProductMap.put(od.getProduct(), count-quantity);
            }
        }
        return unavailableProductMap;
    }

    /**
     * Method checks is user's deposit has enough balance to pay for order If
     * balance is enough - deposit balance decreases on order total price and order
     * payment status become 'PAID' Else method throws exception and order is
     * canceled
     *
     * @param order
     */
    public void payOrder(Order order) {
        long total = calculateOrderTotal(order.getOrderDetailsList());
        Deposit deposit = depositDao.selectUserDeposit(order.getUser().getId());
        long balance = deposit.getBalance();
        if (balance >= total) {
            deposit.setBalance(balance - total);
            depositDao.updateDepositBalance(deposit);
            order.setPaymentStatus(PaymentStatus.PAID);
        } else {
            throw new OutOfBalanceException(total - balance);
        }
    }

    /**
     * Method calculates total order price
     * @param orderDetailsList
     * @return
     */
    public long calculateOrderTotal(List<OrderDetails> orderDetailsList) {
        long total = 0;
        for (OrderDetails od : orderDetailsList) {
            total += od.getProduct().getPrice()*od.getQuantity();
        }
        return total;
    }
    /**
     * Method adds order to sales table
     *
     * @param order
     */
    public void addOrderToSales(Order order) {
        salesService.addSales(order);
    }
    public void decreaseOrderProductsQuantity(List<OrderDetails> orderDetailsList) {
        for(OrderDetails od: orderDetailsList){
            orderDao.changeProductQuantityFromOrder(od.getProduct().getId(),
                    od.getProduct().getQuantity() - od.getQuantity());
        }
    }

    /**
     * Method adds order to database
     * First method checks are products from order in available for purchase
     * If not -method throws exception
     * Then if order payment type is online - method checks is user's deposit has enough balance
     * If not - method throws exception
     * Third method set order to order details and   adds order to database
     * Fourth if order payment status is paid - method adds order to sales
     * At last method descrease quantities of products from order
     * @param order
     * @return
     */
    @Override
    @Transactional
    public long addOrder(Order order) {
        Map<Product, Integer> unavailableProductMap=getUnavailableProductMap(order.getOrderDetailsList());
        if(unavailableProductMap.size()!=0){
            throw new OutOfQuantityException(unavailableProductMap);
        }
       for(OrderDetails od: order.getOrderDetailsList()){
            od.setOrder(order);
        }
        if (order.getPaymentType() == PaymentType.ONLINE) {
            payOrder(order);
        }
        orderDao.addOrder(order);
        if(order.getPaymentStatus()==PaymentStatus.PAID){
            addOrderToSales(order);
        }
       decreaseOrderProductsQuantity(order.getOrderDetailsList());
        messageService.sendMessage();
        return order.getId();
    }

    /**
     * Method sets order to order details and update order in database
     * @param order
     */
    @Override
    @Transactional
    public void updateOrder(Order order) {

        for(OrderDetails od: order.getOrderDetailsList()){
            od.setOrder(order);
        }
        Order original = orderDao.selectOrder(order.getId());
        if (original.getPaymentStatus() == PaymentStatus.NOTPAID && order.getPaymentStatus() == PaymentStatus.PAID) {
            addOrderToSales(order);
        }
        orderDao.updateOrder(order);
    }

    /**
     * Method checks can user see that order
     * Method returns true If user login from order and login of user trying to access order are not the same and role isn't employee

     * @param login
     * @param roleIsEmployee
     */
    public void checkAccess(String orderUserLogin, String login, boolean roleIsEmployee){

        if (!(orderUserLogin.equals(login)) && !roleIsEmployee){
            throw new AccessDeniedException();
        }

    }

    /**
     * Method get order from database by id and loads additional info (user, user address list, products)
     * @param id
     * @return
     */
    @Transactional
    public Order selectOrder(long id, String login, boolean roleIsEmployee) {
        Order order = orderDao.selectOrder(id);
        order.getUser().getAddressList().size();
       checkAccess(order.getUser().getLogin(), login, roleIsEmployee);
        order.getAddress();
        for (OrderDetails od : order.getOrderDetailsList()) {
            od.getProduct();
            od.getQuantity();
        }
        return order;
    }

    @Transactional
    public List<Order> selectOrderList() {
        return orderDao.selectOrderList();
    }

    @Transactional
    public List<Order> selectOrderList(String login) {
        return orderDao.selectOrderList(login);
    }
    @Transactional
    public List<Order> selectOrderList(long id) {
        return orderDao.selectOrderList(id);
    }

    @Transactional
    public List<Order> selectOrderListByOrderStatus(String orderStatus) {
        return orderDao.selectOrderListByOrderStatus(OrderStatus.valueOf(orderStatus));
    }

    @Transactional
    public List<Order> selectOrderListByOrderStatus( String login, String orderStatus) {


        return orderDao.selectOrderListByOrderStatus(login, OrderStatus.valueOf(orderStatus));

    }

    /**
     * Method restores deposit balance
     */
    public void restoreDeposit(long id, List<OrderDetails> orderDetailsList) {
        Deposit deposit = depositDao.selectUserDeposit(id);
        long balance = deposit.getBalance();
        long total = calculateOrderTotal(orderDetailsList);
        deposit.setBalance(balance + total);
        depositDao.updateDepositBalance(deposit);
    }
    /**
     * Method restores sales
     */
    public void restoreSales(List<OrderDetails> orderDetailsList) {
        salesService.restoreSales(orderDetailsList);
    }

    /**
     * Method restores product quantity
     *
     * @param orderDetailsList
     */
    public void restoreOrderProductQuantity(List<OrderDetails> orderDetailsList) {
        int quantity;
        for(OrderDetails od: orderDetailsList){
            quantity = orderDao.selectProductQuantity(od.getProduct().getId());
            orderDao.changeProductQuantityFromOrder(od.getProduct().getId(), quantity + od.getQuantity());
        }
    }
    /**
     * Method deletes order from database At first step method checks is order paid
     * If order is paid, method restore deposit and sales At second step method
     * restore product quantity At third step method deletes all orderdetails and
     * order
     */
    @Transactional
    public void deleteOrder(long id, String login, boolean isRoleEmployee) {
        Order order=orderDao.selectOrder(id);
        checkAccess(order.getUser().getLogin(), login, isRoleEmployee);
        if (order.getPaymentStatus() == PaymentStatus.PAID) {
            restoreDeposit(order.getUser().getId(), order.getOrderDetailsList());
            restoreSales(order.getOrderDetailsList());
        }
        restoreOrderProductQuantity(order.getOrderDetailsList());

        for (OrderDetails od : order.getOrderDetailsList()) {
            orderDao.deleteOrderDetails(od.getId());
        }
        orderDao.deleteOrder(order.getId());
        messageService.sendMessage();

    }
}