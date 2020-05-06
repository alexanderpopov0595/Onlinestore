package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.service.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller handles requests starting with "/"
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Injected sales service
     */
    private SalesService salesService;

    @Autowired
    public HomeController(SalesService salesService) {
        this.salesService=salesService;
    }

    /**
     *  Method gets top-10 products and returns home page
     * @param model
     * @return home page
     */
    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("productList",salesService.getTopProductsList());
        return "home";
    }

}
