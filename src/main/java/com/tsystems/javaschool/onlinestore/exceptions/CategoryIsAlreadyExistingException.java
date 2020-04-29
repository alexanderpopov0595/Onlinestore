package com.tsystems.javaschool.onlinestore.exceptions;

import com.tsystems.javaschool.onlinestore.domain.category.Category;

public class CategoryIsAlreadyExistingException extends RuntimeException {

    private final Category category;

    public CategoryIsAlreadyExistingException (Category category){
        this.category=category;
    }

    /**
     * Method returns error message
     * @return error message
     */
    @Override
    public String getMessage(){
        return "Category name is currently existing";
    }

    public Category getCategory(){
        return category;
    }

}
