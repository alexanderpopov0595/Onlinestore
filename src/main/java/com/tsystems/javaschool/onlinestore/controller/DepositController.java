package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.domain.user.Deposit;
import com.tsystems.javaschool.onlinestore.service.deposit.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Controller is responsible for requests starting with /deposits
 *
 */
@Controller
@RequestMapping("/deposits")
public class DepositController {

    /**
     * injected service to work with deposit
     */
    private DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    /**
     * Method returns deposit add form	 *
     * @param model
     * @return form page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/addDeposit", method = RequestMethod.GET)
    public String showDepositForm(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "deposits/form";
    }

    /**
     * Method gets information about deposit from form and adds deposit to database	 *
     * @param deposit
     * @param principal
     * @return redirect to created deposit page
     */

    @Secured("ROLE_USER")
    @RequestMapping(value = "/addDeposit", method = RequestMethod.POST)
    public String addDepositFromForm(@Valid Deposit deposit, BindingResult result, Principal principal) {
        if(result.hasErrors()){
            return "deposits/form";
        }
        depositService.addDeposit(deposit, principal.getName());
        return "redirect:/deposits/showDeposit";
    }

    /**
     * Method gets deposit from database, puts it in model and returns deposit page	 *
     * @param model
     * @param principal
     * @return deposit page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/showDeposit", method = RequestMethod.GET)
    public String showDeposit(Model model, Principal principal) {
        model.addAttribute("deposit", depositService.selectDeposit(principal.getName()));
        return "deposits/view";
    }

    /**
     * Method gets deposit from database and returns update deposit form	 *
     * @param model
     * @param principal
     * @return deposit update form
     */

    @Secured("ROLE_USER")
    @RequestMapping(value = "/updateDeposit", method = RequestMethod.GET)
    public String showDepositUpdateForm(Model model, Principal principal) {
        model.addAttribute("deposit", depositService.selectDeposit(principal.getName()));
        return "deposits/update";
    }

    /**
     * Method gets deposit from update form, update deposit in database and
     * redirects to deposit page	 *
     * @param deposit
     * @return redirect to deposit page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/updateDeposit", method = RequestMethod.POST)
    public String updateDepositFromForm(@Valid Deposit deposit, BindingResult result) {
        if(result.hasErrors()){
            return "deposits/update";
        }
        depositService.updateDeposit(deposit);
        return "redirect:/deposits/showDeposit";
    }

    /**
     * Method gets deposit with setted balance, which equals to added amount of
     * money	 *
     * @param deposit
     * @return deposit page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/updateDepositBalance", method = RequestMethod.POST)
    public String updateDepositBalanceFromForm(@ModelAttribute("deposit") Deposit deposit) {
        depositService.updateDepositBalance(deposit);
        return "redirect:/deposits/showDeposit";
    }

    /**
     * Method gets deposit from update form, deletes it from database and redirects
     * to user account page	 *
     * @param deposit
     * @return redirect to user account page
     */
    @Secured("ROLE_USER")
    @RequestMapping(value = "/deleteDeposit", method = RequestMethod.POST)
    public String deleteDepositFromForm(@ModelAttribute("deposit") Deposit deposit) {
        depositService.deleteDeposit(deposit);
        return "redirect:/users/account";
    }

}