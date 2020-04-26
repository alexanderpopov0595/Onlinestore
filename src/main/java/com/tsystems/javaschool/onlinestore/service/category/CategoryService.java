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
    public long addCategory(Category category);

    /**
     *  Method updates category
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
     * Method returns category list
     * @return
     */
    public List<Category> selectCategoryList();

    /**
     * Method returns category list with parameters
     * @return
     */
    public List<Category> selectCategoryListWithParameters();

    /**
     * Method deletes category
     * @param category
     */
    public void deleteCategory(Category category);

}