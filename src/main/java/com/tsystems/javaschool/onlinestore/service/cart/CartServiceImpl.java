package com.tsystems.javaschool.onlinestore.service.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.domain.order.OrderDetails;
import com.tsystems.javaschool.onlinestore.domain.product.Product;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger logger=Logger.getLogger(CartServiceImpl.class);
    private ProductDao productDao;

    public CartServiceImpl(ProductDao productDao){
        this.productDao=productDao;
    }
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
    @Transactional
    public void validateCart(Cart cart){
        Product product;
        for(Map.Entry<Product, Integer> entry: cart.getProductMap().entrySet()){
            product=productDao.selectProduct(entry.getKey().getId());
            logger.info("Actual product[id="+product.getId()+"] quantity:"+product.getQuantity());
            logger.info("Product quantity in cart:"+entry.getValue());
            while(entry.getValue()>product.getQuantity()){
                entry.setValue(entry.getValue()-1);
            }
        }
    }

    @Override
    public void removeOrderProducts(Cart cart, List<OrderDetails> orderDetailsList) {
        for(OrderDetails od: orderDetailsList){
           cart.getProductMap().remove(od.getProduct());
        }
    }

    @Override
    public Map<Product, Integer> addProductToOrder(Cart cart, long id) {
        Map<Product, Integer> productMap=new HashMap<Product, Integer>();
        for(Map.Entry<Product, Integer> entry: cart.getProductMap().entrySet()){
            if(entry.getKey().getId()==id){
                productMap.put(entry.getKey(), entry.getValue());
                break;
            }
        }
        return productMap;
    }
}