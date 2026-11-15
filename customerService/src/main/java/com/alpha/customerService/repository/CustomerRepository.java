package com.alpha.customerService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.customerService.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByPhone(String phone);

	Optional<Customer> findById(Long id);

}
