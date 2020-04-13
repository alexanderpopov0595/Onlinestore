package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.domain.category.Category;
import com.tsystems.javaschool.onlinestore.exceptions.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginIsCurrentlyExisting.class)
    public String handleLoginIsCurrenlyExistingException(LoginIsCurrentlyExisting ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", ex.getUser());
        if(ex.getUser().getId()==0){
            return "users/signup";
        }
        else{
            return "users/update";
        }
    }

    @ExceptionHandler(CategoryIsAlreadyExistingException.class)
    public String handleCategoryIsCurrenlyExistingException(CategoryIsAlreadyExistingException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("category", ex.getCategory());
        if(ex.getCategory().getId()==0){
            return "categories/form";
        }
        else{
            return "categories/update";
        }
    }

    @ExceptionHandler(OutOfQuantityException.class)
    public String handleProductOutOfQuantityException(OutOfQuantityException ex, Model model) {
        model.addAttribute("productMap", ex.getProductMap());
        return "errors/quantity";
    }

    @ExceptionHandler(NoDepositExistingException.class)
    public String handleNoDepositExistingException() {
        return "redirect:/deposits/addDeposit";
    }

    @ExceptionHandler(OutOfBalanceException.class)
    public String handleOutOfBalanceException(OutOfBalanceException ex, Model model) {
        model.addAttribute("missmatch", ex.getMissmatch());
        return "errors/balance";
    }

    @ExceptionHandler(DepositAlreadyExistingException.class)
    public String handleDepositAlreadyExistingException(DepositAlreadyExistingException ex) {
        return "errors/deposit";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(){
        return "errors/403";
    }
}
