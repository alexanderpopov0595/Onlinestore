package com.tsystems.javaschool.onlinestore.exceptions;

import com.tsystems.javaschool.onlinestore.domain.product.Product;
import java.util.Map;

public class OutOfQuantityException extends RuntimeException{

    private final Map<Product, Integer> productMap;

    public OutOfQuantityException( Map<Product, Integer> productMap){
        this.productMap=productMap;
    }
    public  Map<Product, Integer> getProductMap(){
        return productMap;
    }
}
