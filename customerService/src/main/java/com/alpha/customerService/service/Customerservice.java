package com.alpha.customerService.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.alpha.customerService.dto.CreateCustomerDto;
import com.alpha.customerService.dto.ResponseStructure;
import com.alpha.customerService.entity.Customer;
import com.alpha.customerService.exception.CustomerAlreadyExistException;
import com.alpha.customerService.exception.CustomerNotExistException;
import com.alpha.customerService.repository.CustomerRepository;

@Service
public class Customerservice {

    @Autowired
    private CustomerRepository customerRepo;

    public ResponseEntity<ResponseStructure<Customer>> createCustomerAccount(
            CreateCustomerDto createCustomerDto) {

        Optional<Customer> customerOptional =
                customerRepo.findByPhone(createCustomerDto.getPhone());

        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exists with this phone number");
        }

        Customer newCustomer = new Customer();

        newCustomer.setName(createCustomerDto.getName());
        newCustomer.setPhone(createCustomerDto.getPhone());
        newCustomer.setMail(createCustomerDto.getMail());
        newCustomer.setAge(createCustomerDto.getAge());
        newCustomer.setGender(createCustomerDto.getGender());

        Customer savedCustomer = customerRepo.save(newCustomer);

        ResponseStructure<Customer> responseStructure =
                new ResponseStructure<>();

        responseStructure.setStatuscode(HttpStatus.CREATED.value());
        responseStructure.setMessage("NEW CUSTOMER CREATED SUCCESSFULLY");
        responseStructure.setData(savedCustomer);

        return new ResponseEntity<>(
                responseStructure, HttpStatus.CREATED);
    }

	public ResponseEntity<ResponseStructure<Customer>> findCustomer(Long id) {
	    
		Optional<Customer> customerOptional = customerRepo.findById(id);
		
		if(customerOptional.isPresent()) {
             
	        ResponseStructure<Customer> response = new ResponseStructure<>();
	        Customer customer = customerOptional.get();

	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("CUSTOMER FOUND SUCCESSFULLY");
	        response.setData(customer);

	        return new ResponseEntity<>(response, HttpStatus.OK);

		}
		throw new CustomerNotExistException("customer not exist..");
	}

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(Long id) {
	    Optional<Customer> custOptional = customerRepo.findById(id);
	    
		if(custOptional.isPresent()) {
			Customer customer = custOptional.get();
			customerRepo.deleteById(id);
			
			ResponseStructure<Customer> rs = new ResponseStructure<Customer>();
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Customer deleted successfully");
			rs.setData(customer);
			return new ResponseEntity<ResponseStructure<Customer>>(rs,HttpStatus.OK);
		}
		throw new CustomerNotExistException("customer not exist..");
	    
	}
}