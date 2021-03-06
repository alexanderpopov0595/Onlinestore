package com.tsystems.javaschool.onlinestore.domain.order;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.tsystems.javaschool.onlinestore.domain.product.Product;

/**
 * Cart stores chosen products and their quantities
 */
public class Cart implements Serializable {

    /**
     * Map contains products and their quantities
     */
    private Map<Product, Integer> productMap;

    public Cart(){
        productMap=new HashMap<>();
    }

    /**
     * Method adds product to map
     * If product is already in map - method increment count
     * Else method adds new product
     * @param product
     */
    public void addProduct(Product product){
        Integer count=productMap.get(product);
        productMap.put(product, count==null? 1:count+1);
    }

    /**
     * Method returns product by id
     * @param id
     * @return product
     */
   public Product getProduct(long id){
        for(Map.Entry<Product, Integer> entry: productMap.entrySet()){
            if(entry.getKey().getId()==id){
                return entry.getKey();
            }
        }
       return null;
   }

    /**
     * Method removes product from map
     * @param id
     */
   public void removeProduct(long id){
        for(Map.Entry<Product, Integer> entry: productMap.entrySet()){
            if(entry.getKey().getId()==id){
                productMap.remove(entry.getKey(),entry.getValue());
                break;
            }
        }
   }

    /**
     * Method removes product from map
     * If product quantity>1 - method decrement product quantity
     * Else method removes product from map
     * @param id
     */
   public void decrementProductQuantity(long id){
       for(Map.Entry<Product, Integer> entry: productMap.entrySet()){
           if(entry.getKey().getId()==id){
               if(entry.getValue()!=0){
                   entry.setValue(entry.getValue()-1);
               }
               else{
                   productMap.remove(entry.getKey(),entry.getValue());
               }
               break;
           }
       }
   }

   public void setProductMap(Map<Product, Integer> productMap){
       this.productMap=productMap;
   }
   public Map<Product, Integer> getProductMap(){
       return productMap;
   }
}