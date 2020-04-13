package com.tsystems.javaschool.onlinestore.service.category;
import java.util.List;

import com.tsystems.javaschool.onlinestore.exceptions.CategoryIsAlreadyExistingException;
import com.tsystems.javaschool.onlinestore.exceptions.LoginIsCurrentlyExisting;
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

    @Autowired
    private CategoryDao categoryDao;

    public void addCategory(Category category) {
        for (Parameter p : category.getParameterList()) {
            p.setCategory(category);
        }
        try{
            categoryDao.addCategory(category);
        }
        catch (DataIntegrityViolationException ex) {
            throw new CategoryIsAlreadyExistingException(category);
        }
    }

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

    public Category selectCategory(long id) {
        Category category = categoryDao.selectCategory(id);
        category.getParameterList().size();
        return category;
    }


    public List<Category> selectCategoryList() {
        return categoryDao.selectCategoryList();
    }

    public List<Category> selectCategoryListWithParameters(){
        List<Category> categoryList = categoryDao.selectCategoryList();
        for (Category c : categoryList) {
            c.getParameterList().size();
        }
        return categoryList;
    }

    public void deleteCategory(Category category) {
        for (Parameter p : category.getParameterList()) {
            categoryDao.deleteParameter(p.getId());
            categoryDao.deleteCategoryParameterProductDetails(p.getId());
        }
        categoryDao.deleteCategoryProducts(category.getId());
        categoryDao.deleteCategory(category.getId());
    }

}