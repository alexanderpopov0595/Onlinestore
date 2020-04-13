package com.tsystems.javaschool.onlinestore.service.category;

import java.util.List;
import com.tsystems.javaschool.onlinestore.domain.category.Category;

/*
 * interface provides methods to work with categories
 */
public interface CategoryService {

    /*
     * method adds new category
     */
    public void addCategory(Category category);

    /*
     * Method updates category
     */
    public void updateCategory(Category category);

    /*
     * methods returns category by id
     */
    public Category selectCategory(long id);



    /*
     * method returns category list
     */
    public List<Category> selectCategoryList();

    /*
     * method returns category list with parameters
     */
    public List<Category> selectCategoryListWithParameters();

    /*
     * method deletes category
     */
    public void deleteCategory(Category category);

}