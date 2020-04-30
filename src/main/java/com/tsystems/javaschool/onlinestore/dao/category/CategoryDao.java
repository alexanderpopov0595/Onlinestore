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
    void addCategory(Category category);

    /**
     * Method updates category in database
     * @param category
     */
     void updateCategory(Category category);

    /**
     * Methods returns category by id
     * @param id
     * @return
     */
    Category selectCategory(long id);

    /**
     * Methods returns category by category name
     * @param name
     * @return category
     */
   Category selectCategory(String name);

    /**
     *  Method returns category list
     * @return category list
     */
   List<Category> selectCategoryList();

    /**
     * Method deletes category by id
     * @param id
     */
     void deleteCategory(long id);

    /**
     * Method deletes category parameter
     * @param id
     */
    void deleteParameter(long id);

    /**
     * Method deletes category products
     * @param id
     */
   void deleteCategoryProducts(long id);

    /**
     * Method delete category parameter product details
     * @param id
     */
    void deleteCategoryParameterProductDetails(long id);
}

