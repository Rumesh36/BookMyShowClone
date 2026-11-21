package com.alpha.customerService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alpha.customerService.dto.CreateCustomerDto;
import com.alpha.customerService.dto.MovieDto;
import com.alpha.customerService.dto.ResponseStructure;
import com.alpha.customerService.dto.SeatDto;
import com.alpha.customerService.entity.Customer;
import com.alpha.customerService.service.Customerservice;


@RestController
public class CustomerController {

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
	
	
	 @GetMapping("/customer/findmovie")
	    public  ResponseEntity<ResponseStructure<List<MovieDto>>> findMovieByName(@RequestParam String name){
		 
	    	return cs.findMovieByName(name);
	    }
	
	 @GetMapping("/customer/shows/seats/available")
	    public ResponseEntity<ResponseStructure<List<SeatDto>>> getAvailableSeats(@RequestParam long id) {
	        return cs. findAvailableSeats(id, "AVAILABLE");
	    }
	
}
