package com.tsystems.javaschool.onlinestore.service.category;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.category.Category;

/**
 * Interface provides methods to work with categories
 */
public interface CategoryService {

    /**
     * Method adds new category
     * @param category
     * @return
     */
    long addCategory(Category category);

    /**
     *  Method updates category
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
     * Method returns category list
     * @return
     */
     List<Category> selectCategoryList();

    /**
     * Method returns category list with parameters
     * @return
     */
    List<Category> selectCategoryListWithParameters();

    /**
     * Method deletes category
     * @param category
     */
    void deleteCategory(Category category);

}