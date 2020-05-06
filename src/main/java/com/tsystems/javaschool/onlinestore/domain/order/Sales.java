package com.tsystems.javaschool.onlinestore.domain.order;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.domain.user.User;

/**
 * Class represents store's sales statistic
 */
@Entity
@Table(name="sales")
public class Sales implements Serializable{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="id_product", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="id_order", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="id_order_details", referencedColumnName = "id")
    private OrderDetails orderDetails;


    @Column(name="revenue")
    private long revenue;

    @Column(name="date")
    private LocalDate date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails=orderDetails;
    }
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }




}