package com.tsystems.javaschool.onlinestore.dao.product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.tsystems.javaschool.onlinestore.domain.product.ProductDTO;
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
        return (Product) entityManager.createQuery("SELECT p FROM Product p WHERE p.id=:id AND p.quantity>0").setParameter("id", id).getSingleResult();
    }


    public List<Product> searchProducts(ProductDTO product) {
        String SQL_SEARCH_QUERY=prepareSql(product);
        List<Product> productList=new ArrayList<>();
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
    public String prepareSql(ProductDTO product){
        String SQL_SELECT = "SELECT * FROM products ";
        String SQL_WHERE = " WHERE quantity>0 ";
        String SQL_NAME = "";
        String SQL_PRICE = "";
        String SQL_CATEGORY = "";
        StringBuilder SQL_PRODUCT_DETAILS =new StringBuilder();
        List<String> product_details = new ArrayList<>();
        if (product.getName() != null) {
            SQL_NAME = " AND name LIKE '%" + product.getName() + "%'";
        }
        if (product.getMinPrice() != 0 || product.getMaxPrice() != 0) {
            if (product.getMaxPrice() == 0 && product.getMinPrice() != 0) {
                SQL_PRICE =  " AND price > " + product.getMinPrice();
            } else if (product.getMinPrice() == 0 && product.getMaxPrice() != 0) {
                SQL_PRICE =  " AND price < " + product.getMaxPrice();
            } else {
                SQL_PRICE =   " AND price BETWEEN " + product.getMinPrice() + " AND " + product.getMaxPrice() + " ";
            }
        }

        if (product.getCategory() != null) {
            SQL_CATEGORY =   " AND id_category=" + product.getCategory().getId() + " ";
        }
        if (!product.getProductDetailsList().isEmpty()) {
            int count=1;
            for (ProductDetails pd : product.getProductDetailsList()) {
                product_details.add(" SELECT id_product FROM product_details WHERE id_parameter="
                        + pd.getParameter().getId() + " AND value='" + pd.getValue() + "'");
            }
            SQL_PRODUCT_DETAILS.append(" AND id IN (SELECT T1.id_product FROM ("+product_details.get(0)+") AS T1 ");
            product_details.remove(0);
            Iterator iterator = product_details.iterator();
            while (iterator.hasNext()) {
                count++;
                SQL_PRODUCT_DETAILS.append(" JOIN ( "+iterator.next()+") AS T"+count+" ON T"+(count-1)+".id_product=T"+count+".id_product ");
            }
            SQL_PRODUCT_DETAILS.append(" )");
        }
        return SQL_SELECT + SQL_WHERE+SQL_NAME + SQL_PRICE + SQL_CATEGORY + SQL_PRODUCT_DETAILS;
    }

    public List<Product> selectProductListByCategory(String category) {
        return  entityManager.createQuery("SELECT p FROM Product p JOIN Category c ON p.category.id=c.id WHERE c.name=:category AND p.quantity>0")
                .setParameter("category", category).getResultList();

    }

    public void deleteProduct(long id) {
        entityManager.createQuery("UPDATE Product p SET p.quantity=0 WHERE p.id=:id").setParameter("id", id)
                .executeUpdate();
    }
    public void deleteProductDetails(long id) {
        entityManager.createQuery("DELETE FROM ProductDetails pd WHERE pd.product.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}