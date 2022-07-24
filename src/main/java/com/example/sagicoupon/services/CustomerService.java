package com.example.sagicoupon.services;

import com.example.sagicoupon.model.Customer;
import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer findCustomerById(long id);

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(long id);
}
