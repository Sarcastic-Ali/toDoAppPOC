package com.telus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.telus.dto.CustomerDto;
import com.telus.mapper.CustomerMapper;
import com.telus.models.Customer;
import com.telus.repositories.CustomerRepo;

@SpringBootTest
class ToDoAppApplicationTests {

	@Autowired
	private CustomerRepo customerRepo;

	@Test
	public void getAllCustomers() {
		List<Customer> list = customerRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	@Test
	public void getCustomerByTheirId() {
		Customer customer = customerRepo.findById(212L).get();
		assertEquals("My customer is completed", customer.getName());
	}
	
	@Test
	public void deleteCustomer() {
		assertThat(customerRepo.existsById(23L)).isFalse();
	}


}
