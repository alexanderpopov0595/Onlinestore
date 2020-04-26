package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.deposit.DepositDao;
import com.tsystems.javaschool.onlinestore.dao.order.OrderDao;
import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.Deposit;
import com.tsystems.javaschool.onlinestore.domain.user.User;
import com.tsystems.javaschool.onlinestore.enums.PaymentStatus;
import com.tsystems.javaschool.onlinestore.exceptions.AccessDeniedException;
import com.tsystems.javaschool.onlinestore.exceptions.CategoryIsAlreadyExistingException;
import com.tsystems.javaschool.onlinestore.exceptions.OutOfBalanceException;
import com.tsystems.javaschool.onlinestore.exceptions.OutOfQuantityException;
import com.tsystems.javaschool.onlinestore.service.message.MessageService;
import com.tsystems.javaschool.onlinestore.service.order.OrderService;
import com.tsystems.javaschool.onlinestore.service.order.OrderServiceImpl;
import com.tsystems.javaschool.onlinestore.service.sales.SalesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private ProductDao productDaoMock = mock(ProductDao.class);
    private OrderDao orderDaoMock = mock(OrderDao.class);
    private SalesService salesServiceMock = mock(SalesService.class);
    private MessageService messageServiceMock = mock(MessageService.class);
    private DepositDao depositDaoMock = mock(DepositDao.class);
    private OrderServiceImpl orderService = new OrderServiceImpl(orderDaoMock, salesServiceMock, messageServiceMock, productDaoMock, depositDaoMock);

    private List<OrderDetails> orderDetailsList;
    private Product p1;
    private Product p2;
    User user;
    Order order;
    Deposit deposit;

    @Before
    public void setUp() {
        orderDetailsList = new ArrayList<>();
        OrderDetails od1 = new OrderDetails();
        p1 = new Product();
        p1.setQuantity(2);
        p1.setId(1);
        p1.setPrice(300);
        od1.setProduct(p1);
        od1.setQuantity(2);
        OrderDetails od2 = new OrderDetails();
        p2 = new Product();
        p2.setId(2);
        p2.setQuantity(2);
        p2.setPrice(300);
        od2.setProduct(p2);
        od2.setQuantity(2);
        orderDetailsList.add(od1);
        orderDetailsList.add(od2);
        user = new User();
        user.setId(1);
        order = new Order();
        order.setUser(user);
        order.setOrderDetailsList(orderDetailsList);
        deposit = new Deposit();
        deposit.setBalance(500);

    }

    @Test
    public void shouldReturnEmptyMap() {
        when(productDaoMock.selectProduct(1)).thenReturn(p1);
        when(productDaoMock.selectProduct(2)).thenReturn(p2);
        orderService.getUnavailableProductMap(orderDetailsList);
        assertEquals(new HashMap<Product, Integer>(), orderService.getUnavailableProductMap(orderDetailsList));
    }

    @Test
    public void shouldReturnUnavailableMap() {
        Product p3 = new Product();
        p3.setId(1);
        p3.setQuantity(1);
        Map<Product, Integer> productMap = new HashMap<>();
        productMap.put(p3, 1);
        when(productDaoMock.selectProduct(1)).thenReturn(p3);
        when(productDaoMock.selectProduct(2)).thenReturn(p2);
        orderService.getUnavailableProductMap(orderDetailsList);
        assertEquals(productMap, orderService.getUnavailableProductMap(orderDetailsList));
    }


    @Test(expected = OutOfBalanceException.class)
    public void shouldTrowOutOfBalanceException() {
        when(depositDaoMock.selectUserDeposit(1)).thenReturn(deposit);
        orderService.payOrder(order);
    }

    @Test
    public void shouldChangeStatus() {
        when(depositDaoMock.selectUserDeposit(1)).thenReturn(deposit);
        doNothing().when(depositDaoMock).updateDepositBalance(deposit);
        deposit.setBalance(2000);
        orderService.payOrder(order);
        assertEquals(PaymentStatus.PAID, order.getPaymentStatus());
    }

    @Test
    public void shouldReturnTotal() {
        assertEquals(1200, orderService.calculateOrderTotal(orderDetailsList));
    }

    @Test(expected = OutOfQuantityException.class)
    public void shouldTrowOutOfQuantityException() {
        Product p3 = new Product();
        p3.setId(1);
        p3.setQuantity(1);
        Map<Product, Integer> productMap = new HashMap<>();
        productMap.put(p3, 1);
        when(productDaoMock.selectProduct(1)).thenReturn(p3);
        when(productDaoMock.selectProduct(2)).thenReturn(p2);
        orderService.addOrder(order);
    }

    @Test(expected = AccessDeniedException.class)
    public void shouldTrowAccessDeniedExceptionOne() {
        orderService.checkAccess("login", "anotherlogin", false);
    }

    @Test
    public void shouldSuccessAccessOne() {
        orderService.checkAccess("login", "login", false);
    }

    @Test
    public void shouldTrowAccessDeniedExceptionTwo() {
        orderService.checkAccess("login", "anotherlogin", true);
    }

    @Test
    public void shouldRestoreDeposit() {
        when(depositDaoMock.selectUserDeposit(1)).thenReturn(deposit);
        doNothing().when(depositDaoMock).updateDepositBalance(deposit);
        orderService.restoreDeposit(1, orderDetailsList);
        assertEquals(1700, deposit.getBalance());

    }
}
