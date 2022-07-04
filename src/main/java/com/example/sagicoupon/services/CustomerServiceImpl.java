package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.CustomerDtoToCustomerConverter;
import com.example.sagicoupon.converters.CustomerToCustomerDtoConverter;
import com.example.sagicoupon.model.Customer;
import com.example.sagicoupon.repositories.CustomerRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@NoArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerToCustomerDtoConverter customerToCustomerDtoConverter;
    private CustomerDtoToCustomerConverter customerDtoToCustomerConverter;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               @Lazy CustomerToCustomerDtoConverter customerToCustomerDtoConverter,
                               @Lazy CustomerDtoToCustomerConverter customerDtoToCustomerConverter) {
        this.customerRepository = customerRepository;
        this.customerToCustomerDtoConverter = customerToCustomerDtoConverter;
        this.customerDtoToCustomerConverter = customerDtoToCustomerConverter;
    }

    public Customer addCustomer(Customer customer) {
        return Optional.ofNullable(customerToCustomerDtoConverter.convert(customer))
                .map(customerRepository::save)
                .map(customerDtoToCustomerConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save customer"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoToCustomerConverter::convert)
                .collect(Collectors.toList());
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerDtoToCustomerConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find customer by id"));
    }

    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = null;
        try {
            existingCustomer = findCustomerById(customer.getId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not update customer because customer not found ");
        }
        return Optional.ofNullable(customerToCustomerDtoConverter.convert(existingCustomer))
                .map(customerRepository::save)
                .map(customerDtoToCustomerConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update customer"));
    }

    public void deleteCustomerById(Long id) {
        Optional.ofNullable(customerToCustomerDtoConverter.convert(findCustomerById(id)))
                .ifPresent(customerRepository::delete);
    }
}
