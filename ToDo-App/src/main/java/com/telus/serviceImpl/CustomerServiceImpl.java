package com.telus.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telus.dto.CustomerDto;
import com.telus.exceptions.CustomerNotFoundException;
import com.telus.mapper.CustomerMapper;
import com.telus.models.Customer;
import com.telus.repositories.CustomerRepo;
import com.telus.service.CustomerService;
import com.telus.util.ResponseConstants;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        logger.info("Enter in createNewCustomer Method..");
        Customer customer = CustomerMapper.dtoToCustomer(customerDto);
        Customer savedCustomer = this.customerRepo.save(customer);
        CustomerDto customerToDto = CustomerMapper.customerToDto(savedCustomer);
        return customerToDto;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        logger.info("Enter in getAllCustomers Method ..");
        List<Customer> allCustomers = this.customerRepo.findAll();
        List<CustomerDto> customerDtoList = allCustomers.stream().map((customer) -> CustomerMapper.customerToDto(customer))
                .collect(Collectors.toList());
        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(long customerId) {
        logger.info("Enter in getCustomerById Method..");
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(ResponseConstants.CUSTOMER_NOT_FOUND + customerId));
        return CustomerMapper.customerToDto(customer);
    }

    @Override
    public CustomerDto updateCustomerById(CustomerDto customerDto, long customerId) {
        logger.info("Enter in updateCustomerById Method..");
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(ResponseConstants.CUSTOMER_NOT_FOUND + customerId));
        customer.setName(customerDto.getName());
        Customer updatedCustomer = this.customerRepo.save(customer);
        return CustomerMapper.customerToDto(updatedCustomer);
    }

    @Override
    public void deleteCustomerById(long customerId) {
        logger.info("Enter in deleteCustomerById Method..");
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(ResponseConstants.CUSTOMER_NOT_FOUND + customerId));
        this.customerRepo.delete(customer);
    }

}
