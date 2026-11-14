package com.alpha.theaterService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.theaterService.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long> {

	Optional<Theater> findByContactNum(long contactNum);

	
}
