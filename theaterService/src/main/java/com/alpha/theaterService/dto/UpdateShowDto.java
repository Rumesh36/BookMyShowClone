package com.alpha.theaterService.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateShowDto {

//	private Long id;
	private LocalDate date;
	private LocalTime time;
	private int screenno;
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
	public int getScreenno() {
		return screenno;
	}
	public void setScreenno(int screenno) {
		this.screenno = screenno;
	}
	
	
}
