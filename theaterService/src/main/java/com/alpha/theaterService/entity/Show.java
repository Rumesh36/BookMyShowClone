package com.alpha.theaterService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
	
	@ManyToOne
	private Movie movie ;
	
	public Show() {
		super();
	}

	public Show(Movie movie, String title, String description, double duration, String language, int screennumber) {
		super();
		this.movie = movie;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.language = language;
		this.screennumber = screennumber;
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
	
	
}
