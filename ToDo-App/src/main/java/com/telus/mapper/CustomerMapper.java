package com.telus.mapper;

import org.apache.commons.lang3.StringUtils;

import com.telus.dto.CustomerDto;
import com.telus.models.Customer;

public class CustomerMapper {

    public static Customer dtoToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = new Customer();
        if (customerDto.getId() != null) {
            customer.setId(customerDto.getId());
        }
        if (!StringUtils.isNotBlank(customerDto.getName())) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        customer.setName(customerDto.getName());
        return customer;
    }

    public static CustomerDto customerToDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();
        if (customer.getId() != null) {
            customerDto.setId(customer.getId());
        }
        if (!StringUtils.isNotBlank(customer.getName())) {
            throw new IllegalArgumentException("Customer name cannot be null or empty");
        }
        customerDto.setName(customer.getName());
        return customerDto;
    }

}
