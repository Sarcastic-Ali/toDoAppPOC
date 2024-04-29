package com.telus.service;

import java.util.List;

import com.telus.dto.CustomerDto;

public interface CustomerService {

    // Create a ToDo
    public CustomerDto createNewCustomer(CustomerDto customerDto);

    // Retrive All Customer
    public List<CustomerDto> getAllCustomers();

    // Retrive Single Customer by Id
    public CustomerDto getCustomerById(long customerId);

    // Update Customer by Id
    public CustomerDto updateCustomerById(CustomerDto customerDto, long customerId);

    // Delete Customer by Id
    public void deleteCustomerById(long customerId);


}
