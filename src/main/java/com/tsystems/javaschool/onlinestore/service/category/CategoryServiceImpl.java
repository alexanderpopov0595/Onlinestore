package com.tsystems.javaschool.onlinestore.service.category;

import java.util.List;
import com.tsystems.javaschool.onlinestore.exceptions.CategoryIsAlreadyExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsystems.javaschool.onlinestore.dao.category.CategoryDao;
import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.domain.category.Parameter;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    /**
     * Injected category dao
     */
    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao){
        this.categoryDao=categoryDao;
    }

    /**
     * Method sets category to parameters and add category to database
     * If category with that name is already existing - method throws exception
     * @param category
     * @return created category id
     */
    public long addCategory(Category category) {
        for (Parameter p : category.getParameterList()) {
            p.setCategory(category);
        }
        try{
            categoryDao.addCategory(category);
        }
        catch (DataIntegrityViolationException ex) {
            throw new CategoryIsAlreadyExistingException(category);
        }
        return category.getId();
    }

    /**
     * Method loads original category and compare its parameters with updated category parameters
     * If some parameters were deleted - method deletes product details for that parameters
     * Then method sets category to parameters and update category in database
     * @param category
     */
    public void updateCategory(Category category) {
        Category original = categoryDao.selectCategory(category.getId());
        for (Parameter p : original.getParameterList()) {
            if (!category.getParameterList().contains(p)) {
                categoryDao.deleteCategoryParameterProductDetails(p.getId());
            }
        }
        for (Parameter p : category.getParameterList()) {
            p.setCategory(category);
        }
        categoryDao.updateCategory(category);
    }

    /**
     * Method loads category by id and loads all parameters
     * @param id
     * @return category
     */
    public Category selectCategory(long id) {
        Category category = categoryDao.selectCategory(id);
        category.getParameterList().size();
        return category;
    }

    public List<Category> selectCategoryList() {
        return categoryDao.selectCategoryList();
    }

    /**
     * Method loads categories and parameters for every category
     * @return category list
     */
    public List<Category> selectCategoryListWithParameters(){
        List<Category> categoryList = categoryDao.selectCategoryList();
        for (Category c : categoryList) {
            c.getParameterList().size();
        }
        return categoryList;
    }

    /**
     * Method deletes category products and category
     * @param category
     */
    public void deleteCategory(Category category) {
        categoryDao.deleteCategoryProducts(category.getId());
        categoryDao.deleteCategory(category.getId());
    }

}