package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.service.message.MessageService;
import com.tsystems.javaschool.onlinestore.service.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller handles requests starting with "/"
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Injected message and sales service
     */
    private MessageService messageService;

    private SalesService salesService;

    @Autowired
    public HomeController(SalesService salesService, MessageService messageService) {

        this.salesService=salesService;
        this.messageService=messageService;
    }

    /**
     *  Method gets top-10 products and returns home page
     * @param model
     * @return home page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(Model model) {
        model.addAttribute("productList",salesService.getTopProductsList());
        return "home";
    }

}