package com.tsystems.javaschool.onlinestore.service.product;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDTO;

/**
 * Interface provides method to work with products
 */
public interface ProductService {

    /**
     * Method adds product to database
     * @param product
     * @return product id
     */
    long addProduct(Product product);

    /**
     * Method updates product in database
     * @param product
     */
     void updateProduct(Product product);

    /**
     * Method selects product by id
     * @param id
     * @return product
     */
   Product selectProduct(long id);

    /**
     * Method return product list by search
     * @param product
     * @return result list
     */
    List<Product> searchProducts(ProductDTO product);

    /**
     * Method returns products list by category
     * @param category
     * @return product list
     */
   List<Product> selectProductListByCategory(String category);

    /**
     * Method deletes product and product details by id
     * @param id product
     */
    void deleteProduct(long id);

}