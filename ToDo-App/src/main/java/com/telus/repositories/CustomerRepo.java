package com.telus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telus.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {


}
