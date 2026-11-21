package com.alpha.theaterService.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterService.dto.CreateShowdto;
import com.alpha.theaterService.dto.CreateTheaterDto;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.dto.UpdateShowDto;
import com.alpha.theaterService.entity.Movie;
import com.alpha.theaterService.entity.Seat;
import com.alpha.theaterService.entity.Show;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.exception.MovieNotExistException;
import com.alpha.theaterService.exception.ShowAlreadyExistException;
import com.alpha.theaterService.exception.ShowNotExistException;
import com.alpha.theaterService.exception.TheaterAlreadyExistWithContactException;
import com.alpha.theaterService.exception.TheaterNotExistException;
import com.alpha.theaterService.repository.AdminRepository;
import com.alpha.theaterService.repository.ShowRepository;
import com.alpha.theaterService.repository.TheaterRepository;

@Service
public class Theaterservice {
	 @Autowired
	    private TheaterRepository theaterRepo; 
	 @Autowired
	 private ShowRepository showrepo;
	 @Autowired
	 private AdminRepository adminrepo;
	 
	 @Autowired
	 private SeatRepository seatRepo;

//	 create theater
	    public ResponseEntity<ResponseStructure<Theater>> createTheaterAccount(CreateTheaterDto createTheaterDto) {

	        Optional<Theater> theaterOptional =
	                theaterRepo.findByContactNum(createTheaterDto.getContactNum());

	        if (theaterOptional.isPresent()) {
	            throw new TheaterAlreadyExistWithContactException();
	        }

	        Theater newthTheater = new Theater();

	        newthTheater.setName(createTheaterDto.getName());
	        newthTheater.setAddress(createTheaterDto.getAddress());
	        newthTheater.setCity(createTheaterDto.getCity());
	        newthTheater.setContactNum(createTheaterDto.getContactNum());
	        newthTheater.setDescription(createTheaterDto.getDescription());
	        newthTheater.setTotalscreens(createTheaterDto.getTotalscreens());
	        newthTheater.setStatus("CLOSED");
	        newthTheater.setMailid(createTheaterDto.getMailid());

	        Theater savedthTheater = theaterRepo.save(newthTheater);

	        ResponseStructure<Theater> responseStructure =
	                new ResponseStructure<Theater>();

	        responseStructure.setStatuscode(HttpStatus.CREATED.value());

	        responseStructure.setMessage("NEW THEATER CREATED SUCCESSFULLY");

	        responseStructure.setData(savedthTheater);

	        return new ResponseEntity<ResponseStructure<Theater>>(
	                responseStructure, HttpStatus.CREATED);
	    }
	    
//	    find theater

		public ResponseEntity<ResponseStructure<Theater>> getTheater(Long id) {
			Optional<Theater>theaterOptional = theaterRepo.findById(id);
			if(theaterOptional.isPresent()) {
				ResponseStructure<Theater> response = new ResponseStructure<>();
		        Theater theater = theaterOptional.get();

		        response.setStatuscode(HttpStatus.OK.value());
		        response.setMessage("Theater FOUND SUCCESSFULLY");
		        response.setData(theater);

		        return new ResponseEntity<>(response, HttpStatus.OK);

			}
			throw new TheaterNotExistException("theater not exist..");
			}

//		delete theater
		public ResponseEntity<ResponseStructure<Theater>> deleteTheater(Long id) {
			
			Optional<Theater> theaterOptional = theaterRepo.findById(id);
			if(theaterOptional.isPresent()) {
				Theater theater = theaterOptional.get();
				
				theaterRepo.deleteById(id);
				ResponseStructure<Theater> rs = new ResponseStructure<Theater>();
				rs.setStatuscode(HttpStatus.OK.value());
		        rs.setMessage("MOVIE DELETED SUCCESSFULLY");
		        rs.setData(null);

		        return new ResponseEntity<>(rs, HttpStatus.OK);
			}
			throw new TheaterNotExistException("theater not exist..");
		}

//		create shows..
		public ResponseEntity<ResponseStructure<Show>> createShow(long id, CreateShowdto t) {

		    // 1. Find theater by ID
		    Optional<Theater> optionaltheater = theaterRepo.findById(id);

		    if (!optionaltheater.isPresent()) {
		        throw new TheaterNotExistException("theater not exist exception..");
		    }

		    Theater theater = optionaltheater.get();

		    // 2. Check whether show already exists
		    boolean exists = showrepo.existsByDateAndTimeAndScreennumberAndTheater(
		            t.getDate(),
		            t.getTime(),
		            t.getScreenno(),
		            theater
		    );

		    if (exists) {
		        throw new ShowAlreadyExistException("Show already exists..");
		    }

		    // 3. Create new Show
		    Show show = new Show();
		    
		    show.setTitle(t.getTitle());
		    show.setDescription(t.getDescription());
		    show.setDate(t.getDate());
		    show.setTime(t.getTime());
		    show.setLanguage(t.getLanguage());
		    show.setDuration(t.getDuration());
		    show.setScreennumber(t.getScreenno());
		    show.setPrice(t.getPrice());
		    
		    //to add list of seats
		    List<Seat> seats = generateSeats(t.getCapacity(), show);
		    show.setSeats(seats);

		    // 4. Find movie by ID
			    Optional<Movie> movieOptional = adminrepo.findById(t.getMovieid());

		    if (!movieOptional.isPresent()) {
		        throw new MovieNotExistException("Movie not found..");
		    }

		    Movie movie = movieOptional.get();

		    // 5. Set movie and theater
		    show.setMovie(movie);
		    show.setTheater(theater);

		    // 6. Add show to theater
		    theater.getOnplayingShows().add(show);

		    // 7. Save show
		    Show savedShow = showrepo.save(show);

		    // 8. Prepare response
		    ResponseStructure<Show> rs = new ResponseStructure<>();

		    rs.setStatuscode(HttpStatus.CREATED.value());
		    rs.setMessage("NEW SHOW CREATED SUCCESSFULLY");
		    rs.setData(savedShow);

		    return new ResponseEntity<>(rs, HttpStatus.CREATED);
		    
	     	}
		
//		generate seats function
		public List<Seat> generateSeats(int capacity,Show show){
			 if (capacity <= 0 ) {
			        throw new IllegalArgumentException(
			                "Capacity must be a positive");
			    }
			    List<Seat> seats = new ArrayList<>();
			    int catCapacity = capacity / 3;
			    int rows = catCapacity / 10;
			    String[] categories = {"SILVER", "GOLD", "DIAMOND"};
			    char row = 'A';
			    for (String category : categories) {
			        for (int i = 1; i <= rows; i++) {
			            for (int j = 1; j <= 10; j++) {
			                Seat seat = new Seat();
			                seat.setSeatno(row + String.valueOf(j));
			                seat.setCategory(category);
			                seat.setStatus("AVAILABLE");
			                seat.setShow(show);
			                seats.add(seat);
			            }
			            row++;
			        }
			    }
			    return seats;
		}
		
		
		
//		find all showss

		public ResponseEntity<ResponseStructure<List<Show>>> findAllShows(Long id) {
			
		    Optional<Theater> optionalTheater = theaterRepo.findById(id);

		    
		    if (optionalTheater.isEmpty()) {
		        throw new TheaterNotExistException("theater not exist");
		    }
		    
		    Theater theater = optionalTheater.get();
		    List<Show> allshows = showrepo.findByTheater(theater);
		    ResponseStructure<List<Show>> rs = new ResponseStructure<>();
		    
		    rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("All Shows Fetched Sucessfully");
			rs.setData(allshows);
			return new ResponseEntity<ResponseStructure<List<Show>>>(rs,HttpStatus.FOUND);
			
		   }

		public ResponseEntity<ResponseStructure<Show>> findShowsById(Long id) {
			Optional<Show> optShow = showrepo.findById(id);
			if(!optShow.isPresent()) {
				throw new ShowNotExistException("show not exists...");
			}
			Show show = optShow.get();
			ResponseStructure<Show> rs = new ResponseStructure<Show>();
			rs.setStatuscode(HttpStatus.FOUND.value());
			rs.setMessage("Show fetched succesfulyy...");
			rs.setData(show);
			return new ResponseEntity<ResponseStructure<Show>>(rs,HttpStatus.FOUND);
		}

		public ResponseEntity<ResponseStructure<Show>> updateShowById(long id, UpdateShowDto t) {
		 
			Optional<Show> optionalShow = showrepo.findById(id);
			if(!optionalShow.isPresent()) {
				throw new ShowNotExistException("show not exist exception");
			}
			
			Show updatedShow = new Show();
			updatedShow.setTime(t.getTime());
			updatedShow.setDate(t.getDate());
			updatedShow.setScreennumber(t.getScreenno());
			
			showrepo.save(updatedShow);
			ResponseStructure<Show> rs = new ResponseStructure<Show>();
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("data updated successfully..");
			rs.setData(updatedShow);
			return new ResponseEntity<ResponseStructure<Show>>(rs,HttpStatus.OK);
	     	}

		public ResponseEntity<ResponseStructure<List<Movie>>> findMovieByName(String name) {
			
			List<Movie> movie =adminrepo.findByTitleContainsIgnoreCase(name);
			if(movie.isEmpty()) {
				throw new MovieNotExistException("movie not exist.");
			}
			ResponseStructure<List<Movie>> rs = new ResponseStructure<>();
			rs.setStatuscode(HttpStatus.OK.value());
			rs.setMessage("Movie fetched successfully..");
			rs.setData(movie);
			return new ResponseEntity<ResponseStructure<List<Movie>>>(rs,HttpStatus.OK);
			
	     	}

//find the list of shows		
		public ResponseEntity<ResponseStructure<List<Show>>> buyTickets(
		        String moviename, String language, LocalDate date) {

		    List<Show> shows = showrepo
		            .findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndDate(
		                    moviename, language, date);

		    ResponseStructure<List<Show>> response = new ResponseStructure<>();
		    response.setStatuscode(HttpStatus.OK.value());
		    response.setMessage("AVAILABLE SHOWS");
		    response.setData(shows);

		    return new ResponseEntity<>(response, HttpStatus.OK);
		}

//		to find all available seats..
		public ResponseEntity<ResponseStructure<List<Seat>>> findAvailableSeats(long id, String string) {
			  if (!showrepo.existsById(id)) {
		            throw new ShowNotExistException("SHOW NOT FOUND");
		        }
			  
			  List<Seat> seats = seatRepo.findByShow_IdAndStatus(id, "AVAILABLE");
			  ResponseStructure<List<Seat>> rs = new ResponseStructure<List<Seat>>();
			  if(seats.isEmpty()) {
				  rs.setStatuscode(HttpStatus.FOUND.value());
				  rs.setMessage("No seats Available");
				  rs.setData(seats);
				  return new ResponseEntity<ResponseStructure<List<Seat>>>(
			                rs, HttpStatus.NOT_FOUND);
			  }
			  
			  rs.setStatuscode(HttpStatus.OK.value());
			    rs.setMessage("Available seats fetched successfully");
			    rs.setData(seats);

			    return new ResponseEntity<>(rs, HttpStatus.OK);
	     	}
		}

