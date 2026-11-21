package com.alpha.theaterService.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.theaterService.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	List<Seat> findByShow_IdAndStatus(long showId, String string);

	
}
