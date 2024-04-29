package com.telus.mapper;

import com.telus.dto.CustomerDto;
import com.telus.models.Customer;

public class CustomerMapper {

    public static Customer dtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        return customer;
    }

    public static CustomerDto customerToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        return customerDto;
    }

}
