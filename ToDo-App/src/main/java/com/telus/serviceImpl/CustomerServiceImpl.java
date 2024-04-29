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


/**
 * Service implementation class for managing Customer entities.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    /**
     * Creates a new customer.
     *
     * @param customerDto The CustomerDto object containing the details of the customer to be created.
     * @return The CustomerDto object representing the newly created customer.
     */
    @Autowired
    private CustomerRepo customerRepo;

    /**
     * Retrieves all customers.
     *
     * @return A list of CustomerDto objects representing all customers.
     */
    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        logger.info("Enter in createNewCustomer Method..");
        Customer customer = CustomerMapper.dtoToCustomer(customerDto);
        Customer savedCustomer = this.customerRepo.save(customer);
        CustomerDto customerToDto = CustomerMapper.customerToDto(savedCustomer);
        return customerToDto;
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The CustomerDto object representing the retrieved customer.
     * @throws CustomerNotFoundException If no customer with the specified ID is found.
     */
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

    /**
     * Updates a customer by its ID.
     *
     * @param customerDto The CustomerDto object containing the updated details of the customer.
     * @param customerId  The ID of the customer to update.
     * @return The CustomerDto object representing the updated customer.
     * @throws CustomerNotFoundException If no customer with the specified ID is found.
     */
    @Override
    public CustomerDto updateCustomerById(CustomerDto customerDto, long customerId) {
        logger.info("Enter in updateCustomerById Method..");
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(ResponseConstants.CUSTOMER_NOT_FOUND + customerId));
        customer.setName(customerDto.getName());
        Customer updatedCustomer = this.customerRepo.save(customer);
        return CustomerMapper.customerToDto(updatedCustomer);
    }


    /**
     * Deletes a customer by its ID.
     *
     * @param customerId The ID of the customer to delete.
     * @throws CustomerNotFoundException If no customer with the specified ID is found.
     */
    @Override
    public void deleteCustomerById(long customerId) {
        logger.info("Enter in deleteCustomerById Method..");
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(ResponseConstants.CUSTOMER_NOT_FOUND + customerId));
        this.customerRepo.delete(customer);
    }

}
