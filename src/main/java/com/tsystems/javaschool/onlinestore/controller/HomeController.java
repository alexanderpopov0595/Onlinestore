package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.service.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    private SalesService salesService;

    @Autowired
    public HomeController(SalesService salesService) {
        this.salesService=salesService;
    }

    /**
     * Method returns home page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(Model model) {
        model.addAttribute("productList",salesService.getTopProductsList());
        return "home";
    }


    /**
     * Method returns 403 page
     */
    @RequestMapping(value="/403",method = RequestMethod.GET)
    public String showAccesssDenied() {

        return "errors/403";
    }
}
