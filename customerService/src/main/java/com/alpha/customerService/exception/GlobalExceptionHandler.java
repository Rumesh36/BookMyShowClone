package com.alpha.customerService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.customerService.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>>handleCustomerAlreadyExist(CustomerAlreadyExistException ex){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage(ex.getMessage());
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler( CustomerNotExistException .class)
	public ResponseEntity<ResponseStructure<String>>handleCustomerNotExist(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Customer is Not Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
}
