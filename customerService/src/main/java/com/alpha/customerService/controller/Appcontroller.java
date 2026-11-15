package com.alpha.customerService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.customerService.dto.CreateCustomerDto;
import com.alpha.customerService.dto.ResponseStructure;
import com.alpha.customerService.entity.Customer;
import com.alpha.customerService.service.Customerservice;

@RestController
public class Appcontroller {

	@Autowired
	private Customerservice cs;
	
	@PostMapping("/customer/createaccount")
	public ResponseEntity<ResponseStructure<Customer>> createCustomer(@RequestBody
			CreateCustomerDto t ) {
		
		return cs.createCustomerAccount(t);
	}
	
	@GetMapping("/customer/find")
	public ResponseEntity<ResponseStructure<Customer>> findCustomer(@RequestParam Long id){
		
		return cs.findCustomer(id);
	}
	
	@DeleteMapping("/customer/delete")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@RequestParam Long id){
		return cs.deleteCustomer(id);
	}
	
	
}
