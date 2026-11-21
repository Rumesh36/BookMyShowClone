package com.alpha.theaterService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.theaterService.entity.Movie;

@Repository
public interface AdminRepository extends JpaRepository<Movie,Long>{

	List<Movie> findByTitleContainsIgnoreCase(String title);

	Optional<Movie>findByTitleIgnoreCase(String name);

}
