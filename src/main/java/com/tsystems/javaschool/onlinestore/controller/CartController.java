package com.tsystems.javaschool.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.domain.product.Product;
import com.tsystems.javaschool.onlinestore.service.cart.CartService;

/*
 * Controller is responsible for requests starting with /cart
 */
@Controller
@RequestMapping("/cart")
public class CartController {


    private static final Logger log = Logger.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /*
     * method returns cart
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("cart", cartService.getCart((Cart)session.getAttribute("cart")));
        return "cart";
    }

    /*
     * method adds product to cart
     */
    @RequestMapping(value = "/addToCart/{id}", method = RequestMethod.GET)
    public String addProductToCart(@PathVariable long id, HttpSession session, HttpServletRequest request) {
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cartService.addProductToCart(cart, id);
        session.setAttribute("cart", cart);
        return "redirect:"+ request.getHeader("Referer");

    }

    /**
     * Method remove all products from cart by setting new cart
     * @param session
     * @return
     */
    @RequestMapping(value = "/removeAll", method = RequestMethod.GET)
    public String removeAllFromCart(HttpSession session) {
        session.setAttribute("cart", new Cart());
        return "redirect:/cart";
    }

    /**
     * Method removes product from cart by id
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeProductFromCart(@PathVariable Long id, HttpSession session) {
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
            cart.removeProduct(id);
            session.setAttribute("cart", cart);
            return "redirect:/cart";
    }

    /**
     * Method decrements product quantity by id
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/removeFromCart/{id}", method = RequestMethod.GET)
    public String removeFromCart(@PathVariable long id, HttpSession session){
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cart.decrementProductQuantity(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }


}