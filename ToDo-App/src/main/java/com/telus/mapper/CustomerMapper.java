package com.telus.mapper;

import org.apache.commons.lang3.StringUtils;

import com.telus.dto.CustomerDto;
import com.telus.models.Customer;


/**
 * Mapper class responsible for converting between Customer and CustomerDto objects.
 */
public class CustomerMapper {


    /**
     * Converts a CustomerDto object to a Customer object.
     *
     * @param customerDto The CustomerDto object to convert.
     * @return A Customer object.
     * @throws IllegalArgumentException If the customerDto is null or if the customer name is null or empty.
     */
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

    /**
     * Converts a Customer object to a CustomerDto object.
     *
     * @param customer The Customer object to convert.
     * @return A CustomerDto object.
     * @throws IllegalArgumentException If the customer is null or if the customer name is null or empty.
     */
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
