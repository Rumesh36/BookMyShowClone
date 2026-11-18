package com.alpha.theaterService.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity   
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private String city;
	private long contactNum;
	private String mailid;
	private int totalscreens;
	private String status;
	
	@OneToOne
	private Adress address;
	@OneToMany
	@JsonIgnore
	private List<Show> onplayingShows;
	
	public Theater() {
	super();
     }

	public Theater(String name, String description, String city, long contactNum, String mailid, int totalscreens,
			String status, Adress address, List<Show> onplayingShows) {
		super();
		this.name = name;
		this.description = description;
		this.city = city;
		this.contactNum = contactNum;
		this.mailid = mailid;
		this.totalscreens = totalscreens;
		this.status = status;
		this.address = address;
		this.onplayingShows = onplayingShows;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getContactNum() {
		return contactNum;
	}

	public void setContactNum(long contactNum) {
		this.contactNum = contactNum;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

	public int getTotalscreens() {
		return totalscreens;
	}

	public void setTotalscreens(int totalscreens) {
		this.totalscreens = totalscreens;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Adress getAddress() {
		return address;
	}

	public void setAddress(Adress address) {
		this.address = address;
	}

	public List<Show> getOnplayingShows() {
		return onplayingShows;
	}

	public void setOnplayingShows(List<Show> onplayingShows) {
		this.onplayingShows = onplayingShows;
	}
	
	
	
}
