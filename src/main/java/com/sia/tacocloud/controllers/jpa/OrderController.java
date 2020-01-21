package com.sia.tacocloud.controllers.jpa;


import com.sia.tacocloud.auxiliary.OrderProperties;
import com.sia.tacocloud.essences.jpa.Order;
import com.sia.tacocloud.essences.jpa.User;
import com.sia.tacocloud.repositories.jpa.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.awt.print.Pageable;

@RequestMapping("/orders")
@Controller
@SessionAttributes("order")
public class OrderController {

    private OrderProperties orderProperties;

    private OrderRepository orderRepository;


    @Autowired
    public OrderController(OrderProperties orderProperties, OrderRepository orderRepository) {
        this.orderProperties = orderProperties;
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }


    @GetMapping
    public String ordersForUser(Model model) {

        Pageable pageable =(Pageable) PageRequest.of(0, orderProperties.getPageSize());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc((User) authentication.getPrincipal(), pageable));

        return "orderList";
    }


    @PostMapping
    public String proccessOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors())
            return "orderForm";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        order.setUser((User) authentication.getPrincipal());

        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
