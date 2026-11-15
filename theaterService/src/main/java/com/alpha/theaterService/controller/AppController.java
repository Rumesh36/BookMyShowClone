package com.alpha.theaterService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterService.dto.CreateTheaterDto;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.service.Theaterservice;
@RestController
public class AppController {

    @Autowired 
    private Theaterservice tser;

    @PostMapping("/theater/createAccount")
    public ResponseEntity<ResponseStructure<Theater>> create(
            @RequestBody CreateTheaterDto t) {

        return tser.createTheaterAccount(t);
    }
    
    @GetMapping("/theater/getTheaters")
    public ResponseEntity<ResponseStructure<Theater>> getTheater(@RequestParam Long id){
    	
    	return tser.getTheater(id);
    }
    
    @DeleteMapping("/theater/deleteTheater")
    public ResponseEntity<ResponseStructure<Theater>>deleteTheater(@RequestParam Long id){
    	return tser.deleteTheater(id);
    }
    
}