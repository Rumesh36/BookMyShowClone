package com.alpha.theaterService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.theaterService.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TheaterAlreadyExistWithContactException.class)
	public ResponseEntity<ResponseStructure<String>> handleTheaterAlreadyExistWithContactException(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage("Theater is Already Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}

}
