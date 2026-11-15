package com.alpha.theaterService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.theaterService.dto.CreateMovieDto;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@PostMapping("/admin/movie/createmovie")
	public ResponseEntity<ResponseStructure<Movie>> createTheater(@RequestBody CreateMovieDto t){
		
		return adminservice.createMovie(t);
	}
	
	@GetMapping("/admin/movie/find")
	public ResponseEntity<ResponseStructure<Movie>>findMovie(@RequestParam Long id){
		return adminservice.findMovieById(id);
	}
	
	@GetMapping("/admin/movie/getAllMovies")
	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovies(){
		return adminservice.getAllMovies();
	}
	
	@DeleteMapping("/admin/movie/delete")
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam Long id){
		return adminservice.deleteMovieById(id);
	}
}
