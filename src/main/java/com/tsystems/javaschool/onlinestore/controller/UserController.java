package com.tsystems.javaschool.onlinestore.controller;

import com.tsystems.javaschool.onlinestore.domain.user.User;
import com.tsystems.javaschool.onlinestore.service.image.ImageService;
import com.tsystems.javaschool.onlinestore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    /**
     * Injected user and image services
     */
    private UserService userService;

    private ImageService imageService;

    @Autowired
    public UserController(UserService userService, ImageService imageService){
        this.userService=userService;
        this.imageService=imageService;
    }

    /**
     * Method shows sign in page     *
     * @param model
     * @return sign in page
     */
    @GetMapping("/signin")
    public String showSignInForm(Model model) {
        return "users/signin";
    }

    /**
     * Method puts user in model and shows sign up form     *
     * @param model
     * @return sign up form
     */
    @GetMapping("/signup")
    public String showUserForm(Model model) {
        model.addAttribute(new User());
        return "users/signup";
    }

    /**
     * Method adds user to database from sign up form
     * At first step method gets user from sign up form and validates it
     * If user is invalid - method returns back sign up page with errors
     * At second step if user role is USER method logs user in
     * At third step method redirects to account page     *
     * @param user from signup form
     * @param model
     * @param request to log user in
     * @return redirect user to account page
     * @throws ServletException
     */
    @PostMapping("/signup")
    public String addUserFromForm(@Valid User user, BindingResult result, Model model, HttpServletRequest request,  @RequestParam(value = "image", required = false) MultipartFile image) throws ServletException {
        if (result.hasErrors()) {
            return "users/signup";
        }
        long id=userService.addUser(user);
        if (user.getRole().equals("ROLE_USER")) {
            request.login(user.getLogin(), user.getPassword());
        }
        imageService.uploadImage(image, "users", id);
        return "redirect:/users/account";
    }

    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE", "ROLE_ADMIN" })
    @GetMapping("/account")
    public String showAccount(Principal principal, Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute(userService.selectUser(principal.getName()));
        }
        return "users/account";
    }

    /**
     * Methods gets user from database, puts it to model and return user update form
     * @param principal
     * @param model
     * @return user update form
     */
    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @GetMapping("/account/update")
    public String showUserUpdateForm(Principal principal, Model model,  @RequestParam(value="error", required = false) String error) {
        model.addAttribute(userService.selectUser(principal.getName()));
        model.addAttribute("error", error);
        return "users/update";
    }

    /**
     * Method updates user in database from update form
     * At first step method gets user from update form and validate it
     * If user is invalid - method returns back update form with errors
     * At second step if user has changed his login method logs user out
     * At third step method redirects to user account page
     * @param user
     * @param model
     * @param principal
     * @param request
     * @return user account page
     */
    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @PostMapping("/account/update")
    public String updateUserFromForm(@Valid User user, BindingResult result, Model model, Principal principal,
                                     HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image)  {
        if(result.hasErrors()){
            return "users/update";
        }
           try {
               userService.updateUser(user);
          }
          catch (Exception ex){
             model.addAttribute("error", "Login is existing");
             return "users/update";
         }

        if (!principal.getName().equals(user.getLogin())) {
            new SecurityContextLogoutHandler().logout(request, null, null);
        }
        imageService.uploadImage(image, "users", user.getId());
        return "redirect:/users/account";
    }


    /**
     * Method deletes user from user update form
     * @param user
     * @return redirect to signout
     */
    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @PostMapping("/account/delete")
    public String deleteAccount(@ModelAttribute("user") User user) {
        userService.deleteUser(user);
        return "redirect:/users/signout";
    }
}


