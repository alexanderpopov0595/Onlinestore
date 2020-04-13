package com.tsystems.javaschool.onlinestore.dao.product;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.product.Product;

/*
 * interface provides methods to work with products in database
 */
public interface ProductDao {

    /*
     * method adds product to database
     */
    public void addProduct(Product product);

    /*
     * method updates product
     */
    public void updateProduct(Product product);

    /*
     * method returns product by id
     */
    public Product selectProduct(long id);


    /*
     * Method returns product list by search
     */
    public List<Product> searchProducts(Product product);

    /*
     * Method returns all products by category name
     */
    public List<Product> selectProductListByCategory(String category);

    /*
     * Method deletes product
     */
    public void deleteProduct(long id);

    /*
     * Method deletes product details
     */
    public void deleteProductDetails(long id);



}