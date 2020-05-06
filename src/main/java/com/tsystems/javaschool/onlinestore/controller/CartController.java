package com.tsystems.javaschool.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.service.cart.CartService;

/**
 * Controller is responsible for requests starting with /cart
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    /**
     * Injected cart service
     */
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Method returns cart
     * @param model
     * @param session
     * @return cart page
     */
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("cart", cartService.getCart((Cart)session.getAttribute("cart")));
        return "cart";
    }

    /**
     * Method gets cart from session, adds product to cart and put cart in session attribute
     * @param id
     * @param session
     * @param request
     * @return previous product page
     */
    @GetMapping("/addToCart/{id}")
    public String addProductToCart(@PathVariable long id, HttpSession session, HttpServletRequest request) {
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cartService.addProductToCart(cart, id);
        session.setAttribute("cart", cart);
        return "redirect:"+ request.getHeader("Referer");

    }

    /**
     * Method remove all products from cart by setting new cart
     * @param session
     * @return cart page
     */
    @GetMapping("/removeAll")
    public String removeAllFromCart(HttpSession session) {
        session.setAttribute("cart", new Cart());
        return "redirect:/cart";
    }

    /**
     * Method removes product from cart by id
     * @param id
     * @param session
     * @return cart page
     */
    @GetMapping( "/remove/{id}")
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
     * @return cart page
     */
    @GetMapping( "/removeFromCart/{id}")
    public String removeFromCart(@PathVariable long id, HttpSession session){
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cart.decrementProductQuantity(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }
}