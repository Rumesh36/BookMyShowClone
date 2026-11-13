
package com.alpha.customerService.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String seatno;
    private LocalTime showtime;
    private String moviename;
    private int screenno;
    private double totalamount;
    private LocalDate date;
    private String language;
 
    private LocalDateTime bookingdate;

    public Ticket() {
        super();
    }

    public Ticket(String username, String seatno, LocalTime showtime,
            String moviename, int screenno, double totalamount,
            LocalDate date, String language, 
            LocalDateTime bookingdate) {
        super();
        this.username = username;
        this.seatno = seatno;
        this.showtime = showtime;
        this.moviename = moviename;
        this.screenno = screenno;
        this.totalamount = totalamount;
        this.date = date;
        this.language = language;
        this.bookingdate = bookingdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeatno() {
        return seatno;
    }

    public void setSeatno(String seatno) {
        this.seatno = seatno;
    }

    public LocalTime getShowtime() {
        return showtime;
    }

    public void setShowtime(LocalTime showtime) {
        this.showtime = showtime;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public int getScreenno() {
        return screenno;
    }

    public void setScreenno(int screenno) {
        this.screenno = screenno;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

//    public String getTheater() {
//        return theater;
//    }

//    public void setTheater(String theater) {
//        this.theater = theater;
//    }

    public LocalDateTime getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(LocalDateTime bookingdate) {
        this.bookingdate = bookingdate;
    }
}
