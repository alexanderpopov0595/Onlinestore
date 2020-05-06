package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.service.category.CategoryService;
import com.tsystems.javaschool.onlinestore.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;

/**
 * Controller is responsible for requests starting with "/categories"
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

    /**
     * Injected category and image service
     */
    private CategoryService categoryService;

    private ImageService imageService;

    @Autowired
    public CategoryController(CategoryService categoryService, ImageService imageService) {
        this.categoryService = categoryService;
        this.imageService=imageService;
    }

    /**
     * Method loads all categories from database and puts them in model
     * @param model
     * @return categories page
     */
    @GetMapping
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
    @GetMapping("/addCategory")
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
     * @param image
     * @return categories page
     */
    @Secured("ROLE_EMPLOYEE")
    @PostMapping("/addCategory")
    public String addCategoryFromForm(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model,  @RequestParam(value = "image", required = false) MultipartFile image) {
        if(result.hasErrors()){
            return "categories/form";
        }
        long id=categoryService.addCategory(category);
        imageService.uploadImage(image, "categories", id);
        return "redirect:/categories";
    }
    /**
     * Method loads category from database by id and returns update form
     * @param id
     * @param model
     * @return category update page
     */
    @Secured("ROLE_EMPLOYEE")
    @GetMapping("/{id}/update")
    public String showCategoryUpdateForm(@PathVariable long id, Model model, @RequestParam(value="error", required = false) String error) {
        model.addAttribute(categoryService.selectCategory(id));
        model.addAttribute("error", error);
        return "categories/update";
    }

    /**
     * Method gets category from form and updates it in database
     * If category is invalid - method returns update form
     * @param category
     * @param result
     * @return category page
     */
    @Secured("ROLE_EMPLOYEE")
    @PostMapping("/{id}/update")
    public String updateCategoryFromForm(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model,   @RequestParam(value = "image", required = false) MultipartFile image) {
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
        imageService.uploadImage(image, "categories", category.getId());
        return "redirect:/categories";
    }
    /**
     * Method deletes category
     * @param category
     * @return categories page
     */
    @Secured("ROLE_EMPLOYEE")
    @PostMapping("/{id}/delete")
    public String deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.deleteCategory(category);
        return "redirect:/categories";
    }
}