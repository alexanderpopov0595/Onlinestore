package com.tsystems.javaschool.onlinestore.service;

import com.tsystems.javaschool.onlinestore.dao.category.CategoryDao;
import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.domain.category.Parameter;
import com.tsystems.javaschool.onlinestore.exceptions.CategoryIsAlreadyExistingException;
import com.tsystems.javaschool.onlinestore.service.category.CategoryService;
import com.tsystems.javaschool.onlinestore.service.category.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private CategoryDao categoryDaoMock = mock(CategoryDao.class);
    private CategoryService categoryService = new CategoryServiceImpl(categoryDaoMock);
    private Category category;

    @Before
    public void setUp() {
        category = new Category();
        category.setId(1);
        Parameter p1 = new Parameter();
        p1.setId(1);
        Parameter p2 = new Parameter();
        p2.setId(2);
        List<Parameter> parameterList = new ArrayList<Parameter>();
        parameterList.add(p1);
        parameterList.add(p2);
        category.setParameterList(parameterList);

    }

    @Test(expected = CategoryIsAlreadyExistingException.class)
    public void shouldThrowException() {
        doThrow(DataIntegrityViolationException.class).when(categoryDaoMock).addCategory(category);
        categoryService.addCategory(category);
    }

    @Test
    public void shouldDeleteParameters() {
        doAnswer(invocation -> {
            category.getParameterList().remove(1);
            return null;
        }).when(categoryDaoMock).deleteCategoryParameterProductDetails(1);

        when(categoryDaoMock.selectCategory(1)).thenReturn(category);
        doNothing().when(categoryDaoMock).updateCategory(category);
        Category updatedCategory = new Category();
        updatedCategory.setId(1);
        List<Parameter> parameterList = new ArrayList<Parameter>();
        Parameter p2 = new Parameter();
        p2.setId(2);
        parameterList.add(p2);
        updatedCategory.setParameterList(parameterList);
        categoryService.updateCategory(updatedCategory);
        assertEquals(1, category.getParameterList().size());
    }
}
