package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.domain.product.ProductDTO;
import com.tsystems.javaschool.onlinestore.service.image.ImageService;
import com.tsystems.javaschool.onlinestore.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.service.category.CategoryService;
import com.tsystems.javaschool.onlinestore.service.product.ProductService;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller is responsible for requests starting with /products
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    /**
     * Injected services
     */
    private ImageService imageService;
    private CategoryService categoryService;
    private ProductService productService;
    private MessageService messageService;

    @Autowired
    public ProductController(ProductService productService, ImageService imageService, MessageService messageService, CategoryService categoryService) {
        this.productService = productService;
        this.imageService=imageService;
        this.messageService=messageService;
        this.categoryService=categoryService;
    }

    /**
     * Method loads categories with parameters and put them and product in model
     * @param model
     * @return form page
     */
    @Secured("ROLE_EMPLOYEE")
    @GetMapping("/addProduct")
    public String showProductForm(Model model) {
        model.addAttribute("categoryList", categoryService.selectCategoryListWithParameters());
        model.addAttribute("product", new Product());
        return "products/form";
    }

    /**
     * Method gets product from form and adds it to database
     * @param product
     * @param image
     * @return redirect to product page
     */
    @Secured("ROLE_EMPLOYEE")
    @PostMapping("/addProduct")
    public String addProductFromForm(@ModelAttribute("product") Product product, @RequestParam(value = "image", required = false) MultipartFile image) {
       long id = productService.addProduct(product);
        imageService.uploadImage(image, "products", id);
        return "redirect:/products/"+ id;
    }

    /**
     * Method gets product by id and put it in model
     * @param id
     * @param model
     * @return product page
     */
    @GetMapping("/{id}")
    public String showProductPage(@PathVariable long id, Model model) {
        model.addAttribute(productService.selectProduct(id));
        return "products/view";
    }

    /**
     * Product gets product by id, loads categories with parameters, put them in model and return update page
     * @param id
     * @param model
     * @return update page
     */
    @Secured("ROLE_EMPLOYEE")
    @GetMapping("/{id}/update")
    public String showProductUpdateForm(@PathVariable long id, Model model, @RequestParam(value="error", required = false) String error) {
        model.addAttribute("categoryList", categoryService.selectCategoryListWithParameters());
        model.addAttribute("product", productService.selectProduct(id));
        model.addAttribute("error", error);
        return "products/update";
    }

    /**
     * Method gets product from update page and updates product in database
     * @param product
     * @param image
     * @return product page
     */
    @Secured("ROLE_EMPLOYEE")
    @PostMapping("/{id}/update")
    public String updateProductFromForm(@ModelAttribute("product") Product product, @RequestParam(value = "image", required = false) MultipartFile image) {
        productService.updateProduct(product);
        imageService.uploadImage(image, "products", product.getId());
        messageService.sendMessage();
        return "redirect:/products/" +product.getId();
    }

    /**
     * Method deletes product by id
     * @param id
     * @return categories page
     */
    @Secured("ROLE_EMPLOYEE")
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        messageService.sendMessage();
        return "redirect:/categories";
    }

    /**
     * Method loads products per category and puts it in model
     * @param category
     * @param model
     * @return product list page
     */
    @GetMapping("/categories/{category}")
    public String showProductListPerCategory(@PathVariable String category, Model model) {
        model.addAttribute("productList", productService.selectProductListByCategory(category));
        return "products/list";
    }

    @PostMapping("/searchByName")
   public String searchProductByName(Model model, HttpServletRequest request){
        String name=request.getParameter("name");
        ProductDTO product=new ProductDTO();
        product.setName(name);
        model.addAttribute("productList", productService.searchProducts(product));
        return "products/list";
   }

    /**
     * Method loads all categories with parameters and returns form for custom product search
     * @param model
     * @return search page
     */
    @GetMapping( "/search")
    public String showCustomProductSearchForm(Model model) {
        model.addAttribute("categoryList", categoryService.selectCategoryListWithParameters());
        model.addAttribute("product", new ProductDTO());
        return "products/search";
    }

    /**
     * Method gets product parameters from custom search and shows found products
     * @param product
     * @param model
     * @return products page
     */
    @PostMapping("/search")
    public String searchProductFromForm(@ModelAttribute("product") ProductDTO product, Model model) {
        model.addAttribute("productList", productService.searchProducts(product));
        return "products/list";
    }



}