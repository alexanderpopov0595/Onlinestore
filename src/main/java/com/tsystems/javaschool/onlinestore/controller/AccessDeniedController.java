package com.tsystems.javaschool.onlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/403")
public class AccessDeniedController {

    /**
     * Method returns 403 page
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showAccesssDenied() {
        return "errors/403";
    }
}