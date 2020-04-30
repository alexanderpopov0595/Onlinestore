package com.tsystems.javaschool.onlinestore.dao.product;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDTO;

/**
 * Interface provides methods to work with products in database
 */
public interface ProductDao {

    /**
     * Method adds product to database
     * @param product
     */
     void addProduct(Product product);

    /**
     * Method updates product
     * @param product
     */
    void updateProduct(Product product);

    /**
     *  Method returns product by id
     * @param id
     * @return product
     */
    Product selectProduct(long id);


    /**
     * Method returns product list by search
     * @param product
     * @return product list
     */
     List<Product> searchProducts(ProductDTO product);

    /**
     * Method returns all products by category name
     * @param category
     * @return product list
     */
     List<Product> selectProductListByCategory(String category);

    /**
     * Method deletes product
     * @param id
     */
     void deleteProduct(long id);

    /**
     * Method deletes product details
     * @param id
     */
     void deleteProductDetails(long id);



}