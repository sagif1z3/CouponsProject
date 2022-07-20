package com.example.sagicoupon.services;

import com.example.sagicoupon.converters.CustomerDtoToCustomerConverter;
import com.example.sagicoupon.converters.CustomerToCustomerDtoConverter;
import com.example.sagicoupon.model.Customer;
import com.example.sagicoupon.repositories.CustomerRepository;
import com.example.sagicoupon.validators.CustomerValidators;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerValidators customerValidators;
    private CustomerToCustomerDtoConverter customerToCustomerDtoConverter;
    private CustomerDtoToCustomerConverter customerDtoToCustomerConverter;

    @Override
    public Customer addCustomer(Customer customer) {
        return Optional.ofNullable(customer)
                .filter(customerValidators::addCustomerValidation)
                .map(customerToCustomerDtoConverter::convertSave)
                .map(customerRepository::save)
                .map(customerDtoToCustomerConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot save customer"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerDtoToCustomerConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerDtoToCustomerConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot find customer by id"));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return Optional.ofNullable(customer)
                .filter(customerValidators::updateCustomerValidation)
                .map(customerToCustomerDtoConverter::convertUpdate)
                .map(customerRepository::save)
                .map(customerDtoToCustomerConverter::convert)
                .orElseThrow(() -> new RuntimeException("Cannot update customer"));
    }

    @Override
    public void deleteCustomerById(Long id) {
        Optional.ofNullable(findCustomerById(id))
                .map(customerToCustomerDtoConverter::convert)
                .ifPresent(customerRepository::delete);
    }
}
