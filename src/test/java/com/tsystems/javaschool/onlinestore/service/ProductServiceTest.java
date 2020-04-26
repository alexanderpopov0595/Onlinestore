package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.product.ProductDao;
import com.tsystems.javaschool.onlinestore.dao.product.ProductDaoImpl;
import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.domain.category.Parameter;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDetails;
import com.tsystems.javaschool.onlinestore.service.message.MessageService;
import com.tsystems.javaschool.onlinestore.service.product.ProductService;
import com.tsystems.javaschool.onlinestore.service.product.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.tags.Param;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductDao productDaoMock = mock(ProductDao.class);
    private ProductService productService = new ProductServiceImpl(productDaoMock);
    private Product product;

    @Before
    public void setUp() {
        product = new Product();
        product.setId(1);
        Category category = new Category();
        category.setId(1);
        Parameter p1 = new Parameter();
        p1.setId(1);
        Parameter p2 = new Parameter();
        p2.setId(2);
        List<Parameter> parameterList = new ArrayList<Parameter>();
        parameterList.add(p1);
        parameterList.add(p2);
        category.setParameterList(parameterList);
        product.setCategory(category);
        ProductDetails pd1 = new ProductDetails();
        pd1.setId(1);
        pd1.setParameter(p1);
        ProductDetails pd2 = new ProductDetails();
        pd2.setId(2);
        pd2.setParameter(p2);
        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(pd1);
        productDetailsList.add(pd2);
        product.setProductDetailsList(productDetailsList);
    }

    @Test
    public void shouldChangeCategoryAndProductDetails() {
        Product newProduct = new Product();
        newProduct.setId(1);
        Category newCategory = new Category();
        newCategory.setId(2);
        Parameter p3 = new Parameter();
        p3.setId(3);
        List<Parameter> newParameterList = new ArrayList<Parameter>();
        newParameterList.add(p3);
        newCategory.setParameterList(newParameterList);
        newProduct.setCategory(newCategory);
        ProductDetails pd3 = new ProductDetails();
        pd3.setId(3);
        pd3.setParameter(p3);
        List<ProductDetails> newProductDetailsList = new ArrayList<>();
        newProductDetailsList.add(pd3);
        newProduct.setProductDetailsList(newProductDetailsList);
        List<ProductDetails> resultProductDetailsList = new ArrayList<>(product.getProductDetailsList());
        when(productDaoMock.selectProduct(1)).thenReturn(product);
        doAnswer(invocation -> {
            resultProductDetailsList.remove(0);
            resultProductDetailsList.remove(0);
            return null;
        }).when(productDaoMock).deleteProductDetails(1);
        doNothing().when(productDaoMock).updateProduct(product);
        productService.updateProduct(newProduct);
        assertEquals(resultProductDetailsList.size(), 0);
    }


}
