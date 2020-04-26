package com.tsystems.javaschool.onlinestore.dao.product;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.product.Product;

/**
 * Interface provides methods to work with products in database
 */
public interface ProductDao {

    /**
     * Method adds product to database
     * @param product
     */
    public void addProduct(Product product);

    /**
     * Method updates product
     * @param product
     */
    public void updateProduct(Product product);

    /**
     *  Method returns product by id
     * @param id
     * @return product
     */
    public Product selectProduct(long id);


    /**
     * Method returns product list by search
     * @param product
     * @return product list
     */
    public List<Product> searchProducts(Product product);

    /**
     * Method returns all products by category name
     * @param category
     * @return product list
     */
    public List<Product> selectProductListByCategory(String category);

    /**
     * Method deletes product
     * @param id
     */
    public void deleteProduct(long id);

    /**
     * Method deletes product details
     * @param id
     */
    public void deleteProductDetails(long id);



}