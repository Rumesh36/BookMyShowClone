package com.alpha.theaterService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterService.dto.CreateShowdto;
import com.alpha.theaterService.dto.CreateTheaterDto;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.dto.UpdateShowDto;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Show;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.service.AdminService;
import com.alpha.theaterService.service.Theaterservice;
@RestController
public class AppController {

    @Autowired 
    private Theaterservice tser;
    
    @Autowired
    private AdminService adminservice;

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
    
    @GetMapping("/theater/movie/getAllMovies")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovies(){
		return adminservice.getAllMovies();
	}
    
    @PostMapping("/theater/createNewShow")
    public ResponseEntity<ResponseStructure<Show>> createNewShow(@RequestParam Long id,@RequestBody CreateShowdto t){
    	return tser.createShow(id,t);
    }
    
    @GetMapping("/theater/findAllShow")
    public ResponseEntity<ResponseStructure<List<Show>>> findPlayingShows(@RequestParam Long id){
    	return tser.findAllShows(id);
    }
    
    @GetMapping("/theater/findShow")
    public ResponseEntity<ResponseStructure<Show>> findShowsByid(@RequestParam Long id){
    	return tser.findShowsById(id);
    }
    
    @PatchMapping("/theater/updateshow")
    public ResponseEntity<ResponseStructure<Show>> updateShowById(@RequestParam long id,@RequestBody UpdateShowDto t){
    	return tser.updateShowById(id,t);
    }
    
//    customer to find movie
    @GetMapping("/customer/findmovie")
    public ResponseEntity<ResponseStructure<Movie>> findMovieByName(@RequestParam String name){
    	return tser.findMovieByName(name);
    }
    
}