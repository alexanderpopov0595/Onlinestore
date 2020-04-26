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

    /**
     * Methods returns category by id
     * @param id
     * @return
     */
    public Category selectCategory(long id);

    /**
     * Methods returns category by category name
     * @param name
     * @return category
     */
    public Category selectCategory(String name);

    /**
     *  Method returns category list
     * @return category list
     */
    public List<Category> selectCategoryList();

    /**
     * Method deletes category by id
     * @param id
     */
    public void deleteCategory(long id);

    /**
     * Method deletes category parameter
     * @param id
     */
    public void deleteParameter(long id);

    /**
     * Method deletes category products
     * @param id
     */
    public void deleteCategoryProducts(long id);

    /**
     * Method delete category parameter product details
     * @param id
     */
    public void deleteCategoryParameterProductDetails(long id);
}

