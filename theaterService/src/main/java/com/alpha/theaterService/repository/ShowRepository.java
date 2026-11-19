package com.alpha.theaterService.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.theaterService.entity.Show;
import com.alpha.theaterService.entity.Theater;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long>{
	 boolean existsByDateAndTimeAndScreennumberAndTheater(
	            LocalDate date,
	            LocalTime time,
	            int screennumber,
	            Theater theater
	    );

	 List<Show> findByTheater(Theater theater);

	 List<Show> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndDate(
			 String moviename,
			 String language,
			LocalDate date);
}
