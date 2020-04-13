package com.tsystems.javaschool.onlinestore.service.cart;

import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;

import java.util.List;
import java.util.Map;


/**
 * Interface provides methods to work with cart
 */
public interface CartService {

    /**
     * Method returns cart from session
     * @param cart
     * @return
     */
    public Cart getCart(Cart cart);

    /**
     * Method adds product to cart by id
     * @param cart
     * @param id
     */
    public void addProductToCart(Cart cart, long id);

    /**
     * Method validates product quantity in cart and in database
     * @param cart
     */
    public void validateCart(Cart cart);

    /**
     * Method removes purchased products from cart
     * @param cart
     * @param orderDetailsList
     */
    public void removeOrderProducts(Cart cart, List<OrderDetails> orderDetailsList);

    /**
     * Method returns product map by product id from cart
     * @param cart
     * @param id
     * @return product map
     */
    public Map<Product, Integer> addProductToOrder(Cart cart, long id);



}