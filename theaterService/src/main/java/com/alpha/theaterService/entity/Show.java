package com.alpha.theaterService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	private String description;
	private double duration;
	private String language;
	private int screennumber;
	
	private LocalDate date;
	private LocalTime time;
	private Double price;
	@ManyToOne
	private Movie movie ;
	@ManyToOne
	private Theater theater;
	
	@OneToMany(cascade =CascadeType.ALL)
	private List<Seat> seats;
	
	public Show() {
		super();
	}

	public Show(Movie movie, String title, String description, double duration, String language, int screennumber, LocalDate date,LocalTime time,Theater theater,Double price) {
		super();
		this.movie = movie;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.screennumber = screennumber;
		this.date=date;
		this.time=time;
		this.theater=theater;
		this.price=price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getScreennumber() {
		return screennumber;
	}

	public void setScreennumber(int screennumber) {
		this.screennumber = screennumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
}
