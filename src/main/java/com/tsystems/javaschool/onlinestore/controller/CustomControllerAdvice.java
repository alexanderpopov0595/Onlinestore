package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.exceptions.*;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    private final static Logger logger=Logger.getLogger(CustomControllerAdvice.class);

    /**
     * Method handles exception when trying to add/update user with already existing login in database
     * @param ex
     * @param model
     * @return signup/update page
     */
    @ExceptionHandler(LoginIsCurrentlyExisting.class)
    public String handleLoginIsCurrenlyExistingException(LoginIsCurrentlyExisting ex, Model model) {
        logger.debug("Trying to add/update user with already existing login");
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", ex.getUser());
        if(ex.getUser().getId()==0){
            return "users/signup";
        }
        else{
            return "users/update";
        }
    }

    /**
     * Method handles exception when trying to add/update category with already existing name
     * @param ex
     * @param model
     * @return form/update page
     */
    @ExceptionHandler(CategoryIsAlreadyExistingException.class)
    public String handleCategoryIsCurrenlyExistingException(CategoryIsAlreadyExistingException ex, Model model) {
        logger.debug("Trying to add category with already existing name");
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("category", ex.getCategory());
        if(ex.getCategory().getId()==0){
            return "categories/form";
        }
        else{
            return "categories/update";
        }
    }

    /**
     * Method handles exception when trying to purchase product with not enough quantity
     * @param ex
     * @param model
     * @return error page
     */
    @ExceptionHandler(OutOfQuantityException.class)
    public String handleProductOutOfQuantityException(OutOfQuantityException ex, Model model) {
        model.addAttribute("productMap", ex.getProductMap());
        return "errors/quantity";
    }

    /**
     *
     * @return
     */
    @ExceptionHandler(NoDepositExistingException.class)
    public String handleNoDepositExistingException() {
        logger.debug("No deposit found");
        return "redirect:/deposits/addDeposit";
    }

    @ExceptionHandler(OutOfBalanceException.class)
    public String handleOutOfBalanceException(OutOfBalanceException ex, Model model) {
        model.addAttribute("missmatch", ex.getMissmatch());
        return "errors/balance";
    }

    @ExceptionHandler(DepositAlreadyExistingException.class)
    public String handleDepositAlreadyExistingException(DepositAlreadyExistingException ex) {
        logger.debug("Deposit is already existing");
        return "errors/deposit";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(){
        return "errors/403";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleEmptyResultException(EmptyResultDataAccessException ex, Model model){
        model.addAttribute("message", "Content not found");
        return "errors/content";
    }
    @ExceptionHandler(NullPointerException.class)
    public String handleEmptyResultException(NullPointerException ex, Model model){
        logger.debug("Nullpointer exception was thrown: "+ ex.getStackTrace());
        model.addAttribute("message", "Content not found");
        return "errors/content";
    }
}
