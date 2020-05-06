package com.tsystems.javaschool.onlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/403")
public class AccessDeniedController {

    /**
     * Method returns 403 page
     * @return 403 error page
     */
    @GetMapping
    public String showAccessDenied() {
        return "errors/403";
    }
}
