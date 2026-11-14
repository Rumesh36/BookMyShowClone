package com.alpha.theaterService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.theaterService.dto.CreateTheaterDto;
import com.alpha.theaterService.dto.ResponseStructure;
import com.alpha.theaterService.entity.Theater;
import com.alpha.theaterService.exception.TheaterAlreadyExistWithContactException;
import com.alpha.theaterService.repository.TheaterRepository;

@Service
public class Theaterservice {
	 @Autowired
	    private TheaterRepository theaterRepo;

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
}
