package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.exceptions.*;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomControllerAdvice  {

    private static final Logger log=Logger.getLogger(CustomControllerAdvice.class);

    /**
     * Method handles exception when trying to add/update user with already existing login in database
     * @param ex
     * @param model
     * @return signup/update page
     */
    @ExceptionHandler(LoginIsCurrentlyExisting.class)
    public String handleLoginIsCurrentlyExistingException(LoginIsCurrentlyExisting ex, Model model) {
        log.debug("Trying to add/update user with already existing login");
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
    public String handleCategoryIsCurrentlyExistingException(CategoryIsAlreadyExistingException ex, Model model) {
        log.debug("Trying to add category with already existing name");
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
        log.debug("No deposit found");
        return "redirect:/deposits/addDeposit";
    }

    @ExceptionHandler(OutOfBalanceException.class)
    public String handleOutOfBalanceException(OutOfBalanceException ex, Model model) {
        model.addAttribute("missmatch", ex.getMissmatch());
        return "errors/balance";
    }

    @ExceptionHandler(DepositAlreadyExistingException.class)
    public String handleDepositAlreadyExistingException(DepositAlreadyExistingException ex) {
        log.debug("Deposit is already existing");
        return "errors/deposit";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(){
        return "errors/403";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleEmptyResultException(EmptyResultDataAccessException ex, Model model){
        return "errors/exception";
    }
   @ExceptionHandler(NullPointerException.class)
    public String handleEmptyResultException(NullPointerException ex, Model model){
        log.error("Nullpointer exception was thrown: "+ ex.getMessage());
       return "errors/exception";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleEmptyResultException(NumberFormatException ex, Model model){
        log.error("Nullpointer exception was thrown: "+ ex.getMessage());
        return "errors/exception";
    }

    @ExceptionHandler(ImageUploadException.class)
    public String handleImageUploadException(ImageUploadException ex, Model model){
        log.error(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        if(ex.getFolder().equals("users")){
            return "redirect:/"+ex.getFolder()+"/account/update?error="+ex.getMessage();
        }
        return "redirect:/"+ex.getFolder()+"/"+ ex.getId()+"/update?error="+ex.getMessage();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String error404(Exception ex) {
        return "errors/exception";
    }




}
