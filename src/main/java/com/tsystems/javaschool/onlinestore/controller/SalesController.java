package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.service.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller is responsible for requests starting with /sales
 */
@RequestMapping(value="/sales")
@Controller
public class SalesController {

    /**
     * Injected service to work with sales
     */
    private SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService){
        this.salesService=salesService;
    }

    /**
     * Method loads most popular products and puts them to model
     * @param model
     * @return products top page
     */
    @RequestMapping(value="/productsTop", method= RequestMethod.GET)
    public String showTopProducts(Model model){
        model.addAttribute("topProductsList", salesService.getTopProductsList());
        return "sales/productsTop";
    }

    /**
     * Method loads most active users and puts them to model
     * @param model
     * @return users top page
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value="/usersTop", method = RequestMethod.GET)
    public String showTopUsers(Model model){
        model.addAttribute("topUsersList", salesService.getTopUserList());
        return "sales/usersTop";
    }

    /**
     * Method loads week sales and put them in model
     * @param model
     * @return week sales page
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value="/weekSales", method = RequestMethod.GET)
    public String showWeekSales(Model model){
        model.addAttribute("salesList", salesService.getWeekSales());
        return "sales/list";
    }

    /**
     * Method loads month sales and put them in model
     * @param model
     * @return month sales page
     */
    @Secured("ROLE_EMPLOYEE")
    @RequestMapping(value="/monthSales", method = RequestMethod.GET)
    public String showMonthSales(Model model){
        model.addAttribute("salesList", salesService.getMonthSales());
        return "sales/list";
    }
}
