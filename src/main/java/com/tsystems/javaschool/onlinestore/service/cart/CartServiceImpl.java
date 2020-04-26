package com.tsystems.javaschool.onlinestore.service.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import javax.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

    /**
     * Injected product dao
     */
    private ProductDao productDao;

    @Autowired
    public CartServiceImpl(ProductDao productDao){
        this.productDao=productDao;
    }

    /**
     * Method returns cart
     * If cart is null - method returns new cart
     * @param cart
     * @return cart
     */
    public Cart getCart(Cart cart) {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    /**
     * Method adds product to cart
     * First method creates product and set id
     * Then method adds product to cart product map
     * Then method validates cart
     * @param id
     */
    public void addProductToCart(Cart cart,long id){
        Product product=productDao.selectProduct(id);
        cart.addProduct(product);
        validateCart(cart);

    }

    /**
     * Method checks actual quantities of all products in cart
     * Iff actual quantity is smaller than quantity in cart - method decrements quantity till cart quantity equals actual quantity
     * @param cart
     */
    @Transactional
    public void validateCart(Cart cart){
        Product product;
        for(Map.Entry<Product, Integer> entry: cart.getProductMap().entrySet()){
            product=productDao.selectProduct(entry.getKey().getId());
            while(entry.getValue()>product.getQuantity()){
                entry.setValue(entry.getValue()-1);
            }
        }
    }

    /**
     * Method removes order products from cart
     * @param cart
     * @param orderDetailsList
     */
    @Override
    public void removeOrderProducts(Cart cart, List<OrderDetails> orderDetailsList) {
        for(OrderDetails od: orderDetailsList){
           cart.getProductMap().remove(od.getProduct());
        }
    }

    /**
     * Method adds chosen product to order
     * @param cart
     * @param id
     * @return product map
     */
    @Override
    public Map<Product, Integer> addProductToOrder(Cart cart, long id) {
        Map<Product, Integer> productMap=new HashMap<>();
        for(Map.Entry<Product, Integer> entry: cart.getProductMap().entrySet()){
            if(entry.getKey().getId()==id){
                productMap.put(entry.getKey(), entry.getValue());
                break;
            }
        }
        return productMap;
    }
}