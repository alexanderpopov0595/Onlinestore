package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Binding;
import javax.validation.Valid;

/**
 * Controller is resposible for requests starting with "/categories"
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    /**
     * Injected service to work with categories
     */
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Method loads all categories from database and puts them in model
     * @param model
     * @return categories page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCategoryList(Model model) {
        model.addAttribute("categoryList", categoryService.selectCategoryList());
        return "categories/list";
    }

    /**
     * Method puts category in model and shows form page
     * @param model
     * @return form
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String showCategoryForm(Model model) {
        model.addAttribute(new Category());
        return "categories/form";
    }

    /**
     * Method gets category from form and adds it to database
     * If category is invalid - method returns form page
     * @param category
     * @param model
     * @param result
     * @return categories page
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategoryFromForm(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        System.out.println("Inside");
        if(result.hasErrors()){
            System.out.println("errors");
            return "categories/form";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    /**
     * Method loads category from database by id and returns update form
     * @param id
     * @param model
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showCategoryEditForm(@PathVariable long id, Model model) {
        model.addAttribute(categoryService.selectCategory(id));
        return "categories/update";
    }

    /**
     * Method gets category from form and updates it in database
     * If category is invalid - method returns update form
     * @param category
     * @param result
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateCategoryFromForm(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "categories/update";
        }
        try{
            categoryService.updateCategory(category);
        }
        catch(Exception ex){
            model.addAttribute("errors", "Category name is already existing");
            return "categories/update";
        }
        return "redirect:/categories";
    }

    /**
     * Method deletes category
     * @param category
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.deleteCategory(category);
        return "redirect:/categories";
    }

}