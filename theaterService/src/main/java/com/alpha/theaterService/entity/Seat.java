package com.alpha.theaterService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String seatno;
	private String category;
	private String status;
	
	@JsonIgnore
	@ManyToOne
	private Show show;
	
	
	public Seat() {
		super();
	}


	public Seat(String seatno, String category, String status, Show show) {
		super();
		this.seatno = seatno;
		this.category = category;
		this.status = status;
		this.show = show;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSeatno() {
		return seatno;
	}


	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Show getShow() {
		return show;
	}


	public void setShow(Show show) {
		this.show = show;
	}
	
	
	
}
