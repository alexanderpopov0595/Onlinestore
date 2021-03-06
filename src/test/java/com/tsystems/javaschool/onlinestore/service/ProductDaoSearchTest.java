package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.product.ProductDaoImpl;
import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.domain.category.Parameter;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDTO;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoSearchTest {

    private ProductDaoImpl productDao = new ProductDaoImpl();
    private ProductDTO product;

    @Before
    public void setUp() {
        product = new ProductDTO();
    }

    @Test
    public void shouldReturnDefaultSQL() {
        assertEquals( "SELECT * FROM products  WHERE quantity>0 ", productDao.prepareSql(new ProductDTO()));
    }

    @Test
    public void shouldReturnSQLWithName() {

        product.setName("product_name");
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND name LIKE '%product_name%'",productDao.prepareSql(product));
    }

    @Test
    public void shouldReturnSQLWithMinPrice() {
        product.setMinPrice(200);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND price > 200", productDao.prepareSql(product));
    }

    @Test
    public void shouldReturnSQLWithMaxPrice() {

        product.setMaxPrice(200);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND price < 200",productDao.prepareSql(product));
    }

    @Test
    public void shouldReturnSQLWithMinAndMaxPrice() {

        product.setMinPrice(200);
        product.setMaxPrice(300);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND price BETWEEN 200 AND 300 ", productDao.prepareSql(product) );
    }

    @Test
    public void shouldReturnSQLWithCategory() {
        Category category = new Category();
        category.setId(1);
        product.setCategory(category);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND id_category=1 ",productDao.prepareSql(product));
    }

    @Test
    public void shouldReturnSQLWithOneProductDetails() {
        Category category = new Category();
        category.setId(1);
        Parameter p = new Parameter();
        p.setId(1);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(p);
        ProductDetails pd = new ProductDetails();
        pd.setValue("value");
        pd.setParameter(p);
        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(pd);
        product.setProductDetailsList(productDetailsList);
        product.setCategory(category);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND id_category=1  AND id IN (SELECT T1.id_product FROM ( SELECT id_product FROM product_details WHERE id_parameter=1 AND value='value') AS T1  )",productDao.prepareSql(product));

    }

    @Test
    public void shouldReturnSQLWithFewProductDetails() {
        Category category = new Category();
        category.setId(1);
        Parameter p1 = new Parameter();
        p1.setId(1);
        Parameter p2 = new Parameter();
        p2.setId(2);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(p1);
        parameterList.add(p2);
        ProductDetails pd1 = new ProductDetails();
        pd1.setValue("value1");
        pd1.setParameter(p1);
        ProductDetails pd2 = new ProductDetails();
        pd2.setValue("value2");
        pd2.setParameter(p2);
        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(pd1);
        productDetailsList.add(pd2);
        product.setProductDetailsList(productDetailsList);
        product.setCategory(category);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND id_category=1  AND id IN (SELECT T1.id_product FROM ( SELECT id_product FROM product_details WHERE id_parameter=1 AND value='value1') AS T1  JOIN (  SELECT id_product FROM product_details WHERE id_parameter=2 AND value='value2') AS T2 ON T1.id_product=T2.id_product  )",productDao.prepareSql(product));

    }

    @Test
    public void shouldReturnSQLWithOneOfTwoProductDetails() {
        Category category = new Category();
        category.setId(1);
        Parameter p1 = new Parameter();
        p1.setId(1);
        Parameter p2 = new Parameter();
        p2.setId(2);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(p1);
        parameterList.add(p2);
        ProductDetails pd1 = new ProductDetails();
        pd1.setValue("value1");
        pd1.setParameter(p1);
        List<ProductDetails> productDetailsList = new ArrayList<>();
        productDetailsList.add(pd1);
        product.setProductDetailsList(productDetailsList);
        product.setCategory(category);
        assertEquals("SELECT * FROM products  WHERE quantity>0  AND id_category=1  AND id IN (SELECT T1.id_product FROM ( SELECT id_product FROM product_details WHERE id_parameter=1 AND value='value1') AS T1  )",productDao.prepareSql(product));

    }
}
