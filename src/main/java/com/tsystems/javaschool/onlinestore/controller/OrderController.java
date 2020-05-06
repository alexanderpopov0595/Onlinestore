package com.tsystems.javaschool.onlinestore.controller;

import java.security.Principal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.tsystems.javaschool.onlinestore.domain.order.Cart;
import com.tsystems.javaschool.onlinestore.domain.order.Order;
import com.tsystems.javaschool.onlinestore.enums.DeliveryType;
import com.tsystems.javaschool.onlinestore.enums.OrderStatus;
import com.tsystems.javaschool.onlinestore.enums.PaymentStatus;
import com.tsystems.javaschool.onlinestore.enums.PaymentType;
import com.tsystems.javaschool.onlinestore.service.cart.CartService;
import com.tsystems.javaschool.onlinestore.service.order.OrderService;
import com.tsystems.javaschool.onlinestore.service.user.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    /**
     * Injected services
     */
    private OrderService orderService;
    private UserService userService;
    private CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService=userService;
        this.cartService=cartService;
    }

    /**
     * Method returns order form page
     * First method gets card from session and adds product map to model
     * Second method gets user info and puts it in model
     * Third method adds order to model
     * Fourth method adds payment and delivery types and payment status and order status to model
     * @param session
     * @param principal
     * @param model
     * @return order form page
     */
    @Secured("ROLE_USER")
    @GetMapping( "/addOrder")
    public String showOrderForm(HttpSession session, Principal principal, Model model) {
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cartService.validateCart(cart);
        model.addAttribute("productMap", cart.getProductMap());
        model.addAttribute("user", userService.selectUser(principal.getName()));
        model.addAttribute("order", new Order());
        model.addAttribute("paymentTypes", new ArrayList<PaymentType>(EnumSet.allOf(PaymentType.class)));
        model.addAttribute("deliveryTypes", new ArrayList<DeliveryType>(EnumSet.allOf(DeliveryType.class)));
        model.addAttribute("paymentStatuses", new ArrayList<PaymentStatus>(EnumSet.allOf(PaymentStatus.class)));
        model.addAttribute("orderStatuses", new ArrayList<OrderStatus>(EnumSet.allOf(OrderStatus.class)));
        return "orders/form";
    }

    @Secured("ROLE_USER")
    @GetMapping("/addOrder/{id}")
    public String showOrderForm(@PathVariable long id,HttpSession session, Principal principal, Model model){
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cartService.validateCart(cart);
        model.addAttribute("productMap", cartService.addProductToOrder(cart, id));
        model.addAttribute("user", userService.selectUser(principal.getName()));
        model.addAttribute("order", new Order());
        model.addAttribute("paymentTypes", new ArrayList<PaymentType>(EnumSet.allOf(PaymentType.class)));
        model.addAttribute("deliveryTypes", new ArrayList<DeliveryType>(EnumSet.allOf(DeliveryType.class)));
        model.addAttribute("paymentStatuses", new ArrayList<PaymentStatus>(EnumSet.allOf(PaymentStatus.class)));
        model.addAttribute("orderStatuses", new ArrayList<OrderStatus>(EnumSet.allOf(OrderStatus.class)));
        return "orders/form";
    }

    /**
     * Method gets order from form, adds order to database and flush cart
     * @param order
     * @param session
     * @param model
     * @return
     */
    @Secured("ROLE_USER")
    //@RequestMapping(value = {"/addOrder",  "/addOrder/**"}, method = RequestMethod.POST)
    @PostMapping(value = {"/addOrder",  "/addOrder/**"})
    public String addOrderFromForm(@ModelAttribute("order") Order order, HttpSession session, Model model) {
        long orderID=orderService.addOrder(order);
        Cart cart = cartService.getCart((Cart)session.getAttribute("cart"));
        cartService.removeOrderProducts(cart, order.getOrderDetailsList());
        session.setAttribute("cart", cart);
        return "redirect:/orders/" + orderID;
    }

    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @GetMapping("/{id}")
    public String showOrder(@PathVariable long id, Principal principal, Model model, HttpServletRequest request) {
        Order order = orderService.selectOrder(id, principal.getName(), request.isUserInRole("ROLE_EMPLOYEE"));
            model.addAttribute("order", order);
            return "orders/view";

    }

    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @GetMapping("/{id}/update")
    public String showOrderUpdateForm(@PathVariable long id, Model model, Principal principal,  HttpServletRequest request) {
        Order order = orderService.selectOrder(id, principal.getName(), request.isUserInRole("ROLE_EMPLOYEE"));
        model.addAttribute("order", order);
            model.addAttribute("paymentTypes", new ArrayList<PaymentType>(EnumSet.allOf(PaymentType.class)));
            model.addAttribute("deliveryTypes", new ArrayList<DeliveryType>(EnumSet.allOf(DeliveryType.class)));
            model.addAttribute("paymentStatuses", new ArrayList<PaymentStatus>(EnumSet.allOf(PaymentStatus.class)));
            model.addAttribute("orderStatuses", new ArrayList<OrderStatus>(EnumSet.allOf(OrderStatus.class)));
            return "orders/update";
    }

    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @PostMapping("/{id}/update")
    public String updateUserOrderFromForm(@ModelAttribute("order") Order order) {
        orderService.updateOrder(order);
        return "redirect:/orders/" + order.getId();

    }

    /**
     * Method loads all orders from database
     * @param model
     * @return order list
     */
    @Secured("ROLE_EMPLOYEE")
    @GetMapping("/all")
    public String showOrdersByOrderStatus(Model model) {
        model.addAttribute("orderList", orderService.selectOrderList());
        return "orders/list";
    }

    /**
     * Method loads orders by order status
     * @param status
     * @param model
     * @return
     */
    @Secured("ROLE_EMPLOYEE")
    @GetMapping("/status/{status}")
    public String showOrdersByOrderStatus(@PathVariable String status, Model model) {
        model.addAttribute("orderList", orderService.selectOrderListByOrderStatus(status));
        return "orders/list";
    }

    /**
     * Method loads all orders by user id

     * @param model
     * @return
     */
    @Secured( "ROLE_EMPLOYEE" )
    @GetMapping("/user/{id}")
    public String showUserOrders(@PathVariable long id ,Model model) {
        model.addAttribute("orderList", orderService.selectOrderList(id));
        return "orders/user";
    }


    /**
     * Method returns all orders by user login
     * @param principal
     * @param model
     * @return
     */
    @Secured("ROLE_USER")
    @GetMapping("/myorders")
    public String showAllUserOrders(Principal principal, Model model){
        model.addAttribute("orderList", orderService.selectOrderList(principal.getName()));
        return "orders/list";
    }

    /**
     * Method returns orders by user login and order status
     * @param status
     * @param principal
     * @param model
     * @return
     */
    @Secured("ROLE_USER")
    @GetMapping("/myorders/{status}")
    public String showUserOrdersByStatus(@PathVariable String status, Principal principal, Model model){
        model.addAttribute("orderList", orderService.selectOrderListByOrderStatus(principal.getName(), status));
        return "orders/list";
    }

    @Secured({ "ROLE_USER", "ROLE_EMPLOYEE" })
    @GetMapping("/{id}/delete")
    public String deleteUserOrder(@PathVariable long id, Principal principal,  HttpServletRequest request) {
        orderService.deleteOrder(id, principal.getName(), request.isUserInRole("ROLE_EMPLOYEE"));
        return "redirect:/users/account";
    }

    @Secured(("ROLE_EMPLOYEE"))
    @PostMapping("/search")
    public String searchOrder(HttpServletRequest request, Model model){
        if(request.getParameter("searchBy").equals("id")){
            Long id=Long.parseLong(request.getParameter("parameter"));
            return "redirect:/orders/"+id;
        }
        model.addAttribute("orderList", orderService.selectOrderList(request.getParameter("parameter")));
        return "orders/user";
    }
}