package com.tsystems.javaschool.onlinestore.domain.product;

import com.tsystems.javaschool.onlinestore.domain.category.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents product dto, used in search operations
 */
public class ProductDTO implements Serializable {

    private String name;

    private long minPrice;

    private long maxPrice;

    private Category category;

    private List<ProductDetails> productDetailsList;

    public ProductDTO() {
        this.productDetailsList = new ArrayList<>();
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setMinPrice(long minPrice){
        this.minPrice=minPrice;
    }
    public long getMinPrice(){
        return minPrice;
    }
    public void setMaxPrice(long maxPrice){
        this.maxPrice=maxPrice;
    }
    public long getMaxPrice(){
        return maxPrice;
    }
    public void setCategory(Category category){
        this.category=category;
    }
    public Category getCategory(){
        return category;
    }
    public void setProductDetailsList(List<ProductDetails> productDetailsList){
        this.productDetailsList=productDetailsList;
    }
    public List<ProductDetails> getProductDetailsList(){
        return productDetailsList;
    }

}
