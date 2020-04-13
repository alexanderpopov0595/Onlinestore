package com.tsystems.javaschool.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.service.category.CategoryService;
import com.tsystems.javaschool.onlinestore.service.product.ProductService;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller is responsible for requests starting with /products
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private CategoryService categoryService;

    /**
     * Injected service to work with products
     */
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Method loads categories with parameters and put them and product in model
     * @param model
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String showProductForm(Model model) {
        model.addAttribute("categoryList", categoryService.selectCategoryListWithParameters());
        model.addAttribute("product", new Product());
        return "products/form";
    }

    /**
     * Method gets product from form and adds it to database
     * @param product
     * @return redirect to product page
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductFromForm(@ModelAttribute("product") Product product) {
        long id = productService.addProduct(product);
        return "redirect:/products/"+ id;
    }

    /**
     * Method gets product by id and put it in model
     * @param id
     * @param model
     * @return product page
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showProdutUpdateForm(@PathVariable long id, Model model) {
        model.addAttribute("categoryList", categoryService.selectCategoryListWithParameters());
        model.addAttribute("product", productService.selectProduct(id));
        return "products/update";
    }

    /**
     * Method gets product from update page and updates product in database
     * @param product
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String updateProductFromForm(@ModelAttribute("product") Product product) {
        productService.updateProduct(product);
        return "redirect:/products/" +product.getId();
    }

    /**
     * Method deletes product by id
     * @param id
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "redirect:/categories";
    }

    /**
     * Method loads products per category and puts it in model
     * @param category
     * @param model
     * @return product list page
     */
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    public String showProductListPerCategory(@PathVariable String category, Model model) {
        model.addAttribute("productList", productService.selectProductListByCategory(category));
        return "products/list";
    }

    @RequestMapping(value="/searchByName", method = RequestMethod.POST)
   public String searchProductByName(Model model, HttpServletRequest request){
        String name=request.getParameter("name");
        Product product=new Product();
        product.setName(name);
        model.addAttribute("productList", productService.searchProducts(product));
        return "products/list";
   }

    /**
     * Method loads all categories with parameters and returns form for custom product search
     * @param model
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showCustomProductSearchForm(Model model) {
        model.addAttribute("categoryList", categoryService.selectCategoryListWithParameters());
        model.addAttribute("product", new Product());
        return "products/search";
    }

    /**
     * Method gets product parameters from custom search and shows found products
     * @param product
     * @param model
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchProductFromForm(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("productList", productService.searchProducts(product));
        return "products/list";
    }

}