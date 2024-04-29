package com.telus.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telus.dto.CustomerDto;
import com.telus.service.CustomerService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/todos")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/create")
    public ResponseEntity<CustomerDto> createNewCustomer(@Valid @RequestBody CustomerDto customerDto)
            throws MethodArgumentNotValidException {
        logger.info("Enter in Create New Customer Handler ..");
        CustomerDto newCustomer = this.customerService.createNewCustomer(customerDto);
        return new ResponseEntity<CustomerDto>(newCustomer, HttpStatus.CREATED);

    }

    // GET: get all customers
    @GetMapping(value = "/")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        logger.info("Enter in Get All Customer Handler..");
        List<CustomerDto> allCustomer = this.customerService.getAllCustomers();
        return new ResponseEntity<List<CustomerDto>>(allCustomer, HttpStatus.OK);
    }

    // GET: get customer by id
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long customerId) {
        logger.info("Enter in Get Customer By Id Handler..");
        CustomerDto customerDto = this.customerService.getCustomerById(customerId);
        return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
    }

    // PATCH: update customer by id
    @PatchMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomerById(@RequestBody CustomerDto customerDto, @PathVariable long customerId) {
        logger.info("Enter in Update Customer Handler..");
        CustomerDto updateCustomerById = this.customerService.updateCustomerById(customerDto, customerId);
        return new ResponseEntity<CustomerDto>(updateCustomerById, HttpStatus.OK);
    }

    // DELETE: delete customer by id
    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long customerId) {
        logger.info("Enter into Delete Customer Handler..");
        this.customerService.deleteCustomerById(customerId);
        return new ResponseEntity<String>("Customer Deleted Successfully !!" + customerId, HttpStatus.OK);
    }


}
