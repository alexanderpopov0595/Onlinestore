package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.service.cart.CartService;
import com.tsystems.javaschool.onlinestore.service.cart.CartServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CartServiceTest {

    private Cart cart;
    private Product product;
    private ProductDao productDaoMock = mock(ProductDao.class);
    private CartService cartService = new CartServiceImpl(productDaoMock);

    @Before
    public void setUpCart() {
        cart = new Cart();
        product = new Product();
        product.setId(1);
        product.setQuantity(1);
        cart.addProduct(product);
    }

    @Test
    public void shouldReturnAddedProductById() {
        assertEquals(product, cart.getProduct(1));
    }

    @Test
    public void shouldReturnNumberOfAddedProducts() {
        cart.addProduct(product);
        int productQuantity = cart.getProductMap().get(product);
        assertEquals(2, productQuantity);
    }

    @Test
    public void shouldRemoveProductAndReturnNull() {
        cart.removeProduct(product.getId());
        assertEquals(null, cart.getProduct(product.getId()));
    }

    @Test
    public void shouldDescreaseProductQuantityAndReturnOne() {
        cart.addProduct(product);
        cart.decrementProductQuantity(product.getId());
        int productQuantity = cart.getProductMap().get(product);
        assertEquals(1, productQuantity);
    }

    @Test
    public void shouldDecreaseQuantityAndReturnOne() {
        when(productDaoMock.selectProduct(product.getId())).thenReturn(product);
        cart.addProduct(product);
        cartService.validateCart(cart);
        int productQuantity = cart.getProductMap().get(product);
        assertEquals(1, productQuantity);
    }

    @Test
    public void shouldRemoveOnlyOrderProducts() {
        Product p1 = new Product();
        p1.setId(2);
        cart.addProduct(p1);
        Product p2 = new Product();
        p2.setId(3);
        cart.addProduct(p2);
        OrderDetails od1 = new OrderDetails();
        od1.setProduct(p1);
        OrderDetails od2 = new OrderDetails();
        od2.setProduct(p2);
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        orderDetailsList.add(od1);
        orderDetailsList.add(od2);
        cartService.removeOrderProducts(cart, orderDetailsList);
        assertEquals(1, cart.getProductMap().size());
        assertEquals(product, cart.getProduct(product.getId()));
    }

    @Test
    public void shouldReturnMapWithChosenProduct() {
        Product p1 = new Product();
        p1.setId(2);
        cart.addProduct(p1);
        Product p2 = new Product();
        p2.setId(3);
        cart.addProduct(p2);
        Map<Product, Integer> expectedMap = new HashMap<>();
        expectedMap.put(product, 1);
        assertEquals(expectedMap, cartService.addProductToOrder(cart, product.getId()));
    }
}
