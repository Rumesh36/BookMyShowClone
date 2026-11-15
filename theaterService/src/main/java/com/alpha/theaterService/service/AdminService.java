package com.alpha.theaterService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterService.dto.CreateMovieDto;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.exception.MovieAlreadyExistException;
import com.alpha.theaterService.exception.MovieNotExistException;
import com.alpha.theaterService.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminrepo;
	
	public ResponseEntity<ResponseStructure<Movie>> createMovie(CreateMovieDto t) {
		Optional<Movie> movieOptional = adminrepo.findByTitleIgnoreCase(t.getTitle());
		
		if(movieOptional.isPresent()) {
			throw new MovieAlreadyExistException("Movie Already Exist..");
		}
		
		Movie newMovie = new Movie();
		
		newMovie.setTitle(t.getTitle());
		newMovie.setDescription(t.getDescription());
		newMovie.setCast(t.getMovieCast());
		newMovie.setDuration(t.getDuration());
		newMovie.setLanguages(t.getLanguages());
		
		Movie savedMovie = adminrepo.save(newMovie);
		
		ResponseStructure<Movie> rs = new ResponseStructure<Movie>();
		rs.setStatuscode(HttpStatus.CREATED.value());

        rs.setMessage("NEW Movie CREATED SUCCESSFULLY");

        rs.setData(savedMovie);

        return new ResponseEntity<ResponseStructure<Movie>>(
                rs, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Movie>> findMovieById(Long id) {
		Optional<Movie> movieOptional = adminrepo.findById(id);
		
		if(movieOptional.isPresent()) {
			ResponseStructure<Movie> response = new ResponseStructure<>();
	        Movie movie = movieOptional.get();

	        response.setStatuscode(HttpStatus.OK.value());
	        response.setMessage("CUSTOMER FOUND SUCCESSFULLY");
	        response.setData(movie);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	        
		}
		throw new MovieNotExistException("Movie not exist");
	}
	
//	delete movies

	public ResponseEntity<ResponseStructure<Movie>> deleteMovieById(Long id) {
		Optional<Movie> movieOptional = adminrepo.findById(id);
		
		if(movieOptional.isPresent()) {
			Movie movie = movieOptional.get();

	        adminrepo.deleteById(id);
	        ResponseStructure<Movie> rs = new ResponseStructure<>();
	        rs.setStatuscode(HttpStatus.OK.value());
	        rs.setMessage("MOVIE DELETED SUCCESSFULLY");
	        rs.setData(movie);

	        return new ResponseEntity<>(rs, HttpStatus.OK);
		}
		throw new MovieNotExistException("Movie not exist");	
	}

//	get all movies
	public ResponseEntity<ResponseStructure<List<Movie>>> getAllMovies() {
		List<Movie> allmovie = adminrepo.findAll();
		ResponseStructure<List<Movie>> rs = new ResponseStructure<>();
		
		if(allmovie.isEmpty()) {
			rs.setStatuscode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("No movie Found");
			rs.setData(allmovie);
			
			  return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
		}
		rs.setStatuscode(HttpStatus.FOUND.value());
		rs.setMessage("All Movies Fetched Sucessfully");
		rs.setData(allmovie);
		
		  return new ResponseEntity<>(rs, HttpStatus.FOUND);
		
	}

}
