package com.tsystems.javaschool.onlinestore.dao.category;

import com.tsystems.javaschool.onlinestore.domain.category.Category;

import java.util.List;

/**
 * Interface provides methods to work with categories in database
 */
public interface CategoryDao {

    /**
     * Method adds category to database
     * @param category
     */
    public void addCategory(Category category);

    /**
     * Method updates category in database
     * @param category
     */
    public void updateCategory(Category category);

    /*
     * methods returns category by id
     */
    public Category selectCategory(long id);

    /*
     * methods returns category by category name
     */
    public Category selectCategory(String name);

    /*
     * method returns category list
     */
    public List<Category> selectCategoryList();

    /*
     * method deletes category
     */
    public void deleteCategory(long id);

    /*
     * method deletes category parameter
     */
    public void deleteParameter(long id);

    /*
     * method deletes category products
     */
    public void deleteCategoryProducts(long id);

    /*
     * method delete category parameter product details
     */
    public void deleteCategoryParameterProductDetails(long id);
}

