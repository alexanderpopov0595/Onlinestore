package com.tsystems.javaschool.onlinestore.domain.order;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

import com.tsystems.javaschool.onlinestore.domain.product.Product;

@Entity
@Table(name = "order_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    @Column(name="quantity")
    private int quantity;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public int getQuantity(){
        return quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return "Order details product id: "+ product.getId();
    }

}