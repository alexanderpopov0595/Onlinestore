package com.tsystems.javaschool.onlinestore.service.product;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.product.Product;

/**
 * Interface provides method to work with products
 */
public interface ProductService {

    /**
     * Method adds product to database
     * @param product
     * @return product id
     */
    public long addProduct(Product product);

    /**
     * Method updates product in database
     * @param product
     */
    public void updateProduct(Product product);

    /**
     * Method selects product by id
     * @param id
     * @return product
     */
    public Product selectProduct(long id);

    /**
     * Method return product list by search
     * @param product
     * @return result list
     */
    public List<Product> searchProducts(Product product);

    /**
     * Method returns products list by category
     * @param category
     * @return product list
     */
    public List<Product> selectProductListByCategory(String category);

    /**
     * Method deletes product and product details by id
     * @param id product
     */
    public void deleteProduct(long id);

}