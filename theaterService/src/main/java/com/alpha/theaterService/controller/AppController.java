package com.alpha.theaterService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}