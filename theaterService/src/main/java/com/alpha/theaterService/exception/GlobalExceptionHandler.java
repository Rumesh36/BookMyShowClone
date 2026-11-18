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
	@ExceptionHandler(TheaterNotExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleTheaterNotExist(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Theater is not  Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
//	movies
	
	@ExceptionHandler(MovieAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleMovieAlreadyExist(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage("Movie already  Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MovieNotExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleMovieNotExist(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Movie is not  Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ShowAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleShowAlreadyExist(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.CONFLICT.value());
		rs.setMessage("Show already  Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ShowNotExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleShowNotExist(){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("show is not  Exist...");
		rs.setData("");
		
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}

}
