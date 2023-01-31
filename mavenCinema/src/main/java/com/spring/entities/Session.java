package com.spring.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Session implements Comparable<Object>, Serializable {
	private String sessionID;
	private String hallID;
	private Date dateAndTime;
	private int capacity;
	private float ticketPrice;
	private String movieID;

	public String getHallID() {
		return hallID;
	}

	public void setHallID(String hallID) {
		this.hallID = hallID;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public Session(String hallID, String date, String time, int capacity, float ticketPrice, String movieID) {
		this.sessionID = "";
		this.hallID = hallID;
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		try {
			System.out.println(date + " " + time);
			this.dateAndTime = d1.parse(date + " " + time);
		} catch (ParseException e) {
			System.out.println("Bad date format");
		}
		this.capacity = capacity;
		this.ticketPrice = ticketPrice;
		this.movieID = movieID;
	}

	public Session() {
		// TODO Auto-generated constructor stub
	}

	public String getMovieID() {
		return movieID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return String.format("ID: %s\nhall: %s\nDate & time: %s\ncapacity: %d\nticketPrice: %f\nmovie: %s\n", sessionID,
				hallID, dateAndTime, capacity, ticketPrice, movieID);
	}

	@Override
	public boolean equals(Object obj) {
		Session s;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == this.getClass()) {
			s = (Session) obj;
			return this.sessionID.equals(s.sessionID);
		} else
			return false;
	}

	@Override
	public int compareTo(Object o) {
		return this.dateAndTime.compareTo(((Session) o).dateAndTime);
	}
}