package com.example.sagicoupon.controllers;

import com.example.sagicoupon.model.Customer;
import com.example.sagicoupon.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addnew")
    public Customer addNewCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{id}/show")
    public Customer showCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

    @GetMapping("/showall")
    public List<Customer> showAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/update")
    public Customer updateCustomer (@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/{id}/delete")
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }
}
