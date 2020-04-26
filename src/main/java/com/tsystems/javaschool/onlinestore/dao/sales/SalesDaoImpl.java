package com.tsystems.javaschool.onlinestore.dao.sales;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.domain.order.Sales;
import org.springframework.stereotype.Repository;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.User;

@Repository
public class SalesDaoImpl implements SalesDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addSales(Sales sales) {
        entityManager.persist(sales);
    }

    @Override
    public void restoreSales(long id) {
        entityManager.createQuery("DELETE FROM Sales s WHERE s.orderDetails.id=:id").setParameter("id", id).executeUpdate();
    }



    public List<Product> getTopProductsList() {
        List<Object[]> objList=entityManager.createNativeQuery("SELECT id, name, price, quantity, volume, weight, id_category FROM products "
                + "JOIN ( "
                + "SELECT id_product, COUNT(id_product) AS count "
                + "FROM sales "
                + "GROUP BY id_product "
                + "ORDER BY count DESC) AS T2 "
                + "	ON products.id=T2.id_product"
                +" WHERE quantity>0"
                +" ORDER BY T2.count DESC LIMIT 10").getResultList();
        List<Product> productList=new ArrayList<Product>();
        for(Object[] o: objList) {
            Product product=new Product();
            product.setId(((BigInteger) o[0]).longValue());
            product.setName(o[1].toString());
            product.setPrice(((BigInteger) o[2]).longValue());
            product.setQuantity((Integer) o[3]);
            product.setVolume(((BigInteger) o[4]).longValue());
            product.setWeight(((BigInteger) o[5]).longValue());
            productList.add(product);
        }
        return productList;
    }

    public List<User> getTopUserList() {
        List<Object[]> objList=entityManager.createNativeQuery("SELECT id, firstName, lastName, email, birthday, login, password FROM users JOIN ( SELECT id_user, COUNT(id_user) AS count FROM sales GROUP BY id_user ORDER BY count DESC) AS T2 ON users.id=T2.id_user WHERE status='ACTIVE' ORDER BY T2.count DESC LIMIT 10").getResultList();
        List<User> userList=new ArrayList<User>();
        for(Object[] o:objList){
            User user=new User();
            user.setId(((BigInteger) o[0]).longValue());
            user.setFirstName(o[1].toString());
            user.setLastName(o[2].toString());
            user.setEmail(o[3].toString());
            user.setBirthday(o[4].toString());
            user.setLogin(o[5].toString());
            user.setPassword(o[6].toString());
            userList.add(user);
        }
        return userList;
    }

    public List<Sales> getWeekSales() {
        List<Object[]>  objList=entityManager.createNativeQuery( "SELECT date, revenue, id_order, id_user FROM sales WHERE WEEK(date)=WEEK(CURDATE())").getResultList();
        List<Sales> saleList=new ArrayList<Sales>();
        for(Object[] o: objList){
            Sales sales=new Sales();
            sales.setDate(LocalDate.parse(o[0].toString()));
            sales.setRevenue(((BigInteger) o[1]).longValue());

            long id_order=((BigInteger) o[2]).longValue();
            Order order=new Order();
            order.setId(id_order);
            sales.setOrder(order);

            long id_user=((BigInteger) o[3]).longValue();
            User user=new User();
            user.setId(id_user);
            sales.setUser(user);

            saleList.add(sales);


        }
        return saleList;
    }

    public List<Sales> getMonthSales() {
        List<Object[]>  objList=entityManager.createNativeQuery( "SELECT date, revenue, id_order, id_user FROM sales WHERE MONTH(date)=MONTH(CURDATE())").getResultList();
        List<Sales> saleList=new ArrayList<Sales>();
        for(Object[] o: objList){
            Sales sales=new Sales();
            sales.setDate(LocalDate.parse(o[0].toString()));
            sales.setRevenue(((BigInteger) o[1]).longValue());

            long id_order=((BigInteger) o[2]).longValue();
            Order order=new Order();
            order.setId(id_order);
            sales.setOrder(order);

            long id_user=((BigInteger) o[3]).longValue();
            User user=new User();
            user.setId(id_user);
            sales.setUser(user);

            saleList.add(sales);


        }
        return saleList;
    }




}