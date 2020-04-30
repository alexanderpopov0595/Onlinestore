package com.tsystems.javaschool.onlinestore.service.product;

import java.util.List;

import com.tsystems.javaschool.onlinestore.domain.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDetails;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    /**
     * Injected productDao
     */
    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao){
        this.productDao=productDao;
    }

    /**
     * Method sets product details for every product and add product to database
     * @param product
     * @return create product id
     */
    public long addProduct(Product product) {
        for (ProductDetails pd : product.getProductDetailsList()) {
            pd.setProduct(product);
        }
        productDao.addProduct(product);
        return product.getId();
    }

    /**
     * Method loads original product and compare original product category with updated product category
     * If category was changed - method deletes all product details
     * Then method sets all product details to product and add product to database
     * @param product
     */
    public void updateProduct(Product product) {
        Product original = productDao.selectProduct(product.getId());
        if (original.getCategory().getId() != product.getCategory().getId()) {
            for (ProductDetails pd : original.getProductDetailsList()) {
                productDao.deleteProductDetails(pd.getId());
            }
        }
        for (ProductDetails pd : product.getProductDetailsList()) {
            pd.setProduct(product);
        }
        productDao.updateProduct(product);
    }

    /**
     * Method loads product by id and loads product details and category parameters names
     * @param id
     * @return product
     */
    public Product selectProduct(long id) {
        Product product = productDao.selectProduct(id);
        for (ProductDetails pd : product.getProductDetailsList()) {
            pd.getParameter();
        }
        product.getCategory().getParameterList().size();
        return product;
    }


    public List<Product> selectProductListByCategory(String category) {
        return productDao.selectProductListByCategory(category);
    }

    public List<Product> searchProducts(ProductDTO product) {
        return productDao.searchProducts(product);
    }

    public void deleteProduct(long id) {
        productDao.deleteProduct(id);
    }
}