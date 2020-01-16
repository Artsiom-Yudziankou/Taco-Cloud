package com.sia.tacocloud.controllers.jdbc;


import com.sia.tacocloud.essences.jdbc.Order;
import com.sia.tacocloud.repositories.jdbc.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@RequestMapping("/orders")
@Controller
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping("/orders")
    public String proccessOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors())
            return "orderForm";
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
