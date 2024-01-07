package me.kqlqk.event_horizon.controller;

import me.kqlqk.event_horizon.model.Customer;
import me.kqlqk.event_horizon.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public String getCustomerPage(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);

        model.addAttribute("customer", customer);

        return "customer/CustomerPage";
    }
}
