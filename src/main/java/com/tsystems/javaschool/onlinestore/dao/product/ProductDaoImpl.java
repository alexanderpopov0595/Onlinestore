package com.tsystems.javaschool.onlinestore.dao.product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDetails;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    public Product selectProduct(long id) {
        return entityManager.find(Product.class, id);
    }


    public List<Product> searchProducts(Product product) {

        String SQL_SELECT = "SELECT * FROM products ";
        String SQL_WHERE = " WHERE ";
        String SQL_AND = "";
        String SQL_NAME = "";
        String SQL_PRICE = "";
        String SQL_CATEGORY = "";
        String SQL_PRODUCT_DETAILS = "";
        List<String> product_details = new ArrayList<String>();
        List<Product> products = new ArrayList<Product>();


        if (product.getName() != null) {
            SQL_NAME = SQL_WHERE + " name LIKE '%" + product.getName() + "%'";
            SQL_WHERE = "";
            SQL_AND = " AND ";
        }



        if (product.getMinPrice() != 0 || product.getMaxPrice() != 0) {

            if (product.getMaxPrice() == 0 && product.getMinPrice() != 0) {
                SQL_PRICE = SQL_WHERE + SQL_AND + " price > " + product.getMinPrice();
            } else if (product.getMinPrice() == 0 && product.getMaxPrice() != 0) {
                SQL_PRICE = SQL_WHERE + SQL_AND + " price < " + product.getMaxPrice();
            } else {
                SQL_PRICE = SQL_WHERE + SQL_AND + " price BETWEEN " + product.getMinPrice() + " AND "
                        + product.getMaxPrice() + " ";
            }
            SQL_WHERE = "";
        }

        if (product.getCategory() != null) {
            SQL_CATEGORY = SQL_WHERE + SQL_AND + " id_category=" + product.getCategory().getId() + " ";
            SQL_WHERE = "";
        }

        if (product.getProductDetailsList().size() != 0) {
            int count=1;
            for (ProductDetails pd : product.getProductDetailsList()) {
                product_details.add(" SELECT id_product FROM product_details WHERE id_parameter="
                        + pd.getParameter().getId() + " AND value='" + pd.getValue() + "'");
            }
            SQL_PRODUCT_DETAILS = " AND id IN (SELECT T1.id_product FROM ("+product_details.get(0)+") AS T1 ";
            product_details.remove(0);
            Iterator iterator = product_details.iterator();
            while (iterator.hasNext()) {
                count++;
                SQL_PRODUCT_DETAILS += " JOIN ( "+iterator.next()+") AS T"+count+" ON T"+(count-1)+".id_product=T"+count+".id_product ";
              //  if (iterator.hasNext()) {
                //    SQL_PRODUCT_DETAILS += " UNION ";
              //  }
            }
            SQL_PRODUCT_DETAILS += " )";

        }
        String SQL_SEARCH_QUERY = SQL_SELECT + SQL_NAME + SQL_PRICE + SQL_CATEGORY + SQL_PRODUCT_DETAILS;
        System.out.println("Result quary: "+SQL_SEARCH_QUERY);
        //sql
        List<Product> productList=new ArrayList<Product>();
        Product p;
        List<Object[]> objList=entityManager.createNativeQuery(SQL_SEARCH_QUERY).getResultList();
        for(Object[] o: objList) {
            p=new Product();

            p.setCategory(new Category());
            p.setId(((BigInteger)o[0]).longValue());
            p.setName(o[1].toString());
            p.setPrice(((BigInteger) o[2]).longValue());
            p.setQuantity((Integer) o[3]);
            p.setVolume(((BigInteger) o[4]).longValue());
            p.setWeight(((BigInteger) o[5]).longValue());
            p.getCategory().setId(((BigInteger) o[6]).longValue());
            productList.add(p);
        }
        return productList;



    }

    public List<Product> selectProductListByCategory(String category) {
        return (List<Product>) entityManager.createQuery("SELECT p FROM Product p JOIN Category c ON p.category.id=c.id WHERE c.name=:category")
                .setParameter("category", category).getResultList();

    }

    public void deleteProduct(long id) {
        entityManager.createQuery("DELETE FROM Product p WHERE p.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
    public void deleteProductDetails(long id) {
        entityManager.createQuery("DELETE FROM ProductDetails pd WHERE pd.product.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}