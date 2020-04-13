package com.tsystems.javaschool.onlinestore.service.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDetails;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public long addProduct(Product product) {
        for (ProductDetails pd : product.getProductDetailsList()) {
            pd.setProduct(product);
        }
        productDao.addProduct(product);
        return product.getId();
    }

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

    public List<Product> searchProducts(Product product) {
        return productDao.searchProducts(product);
    }

    public void deleteProduct(long id) {
        productDao.deleteProductDetails(id);
        productDao.deleteProduct(id);
    }

}