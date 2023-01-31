package com.spring.entities;

import java.io.Serializable;

public class Movie implements Comparable<Object>, Serializable {
	private String movieID;
	private String title;
	private int minAge;

	public Movie(String title, int minAge) {
		this.movieID = "";
		this.title = title;
		this.minAge = minAge;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public String getMovieID() {
		return movieID;
	}

	public int getMinAge() {
		return minAge;
	}

	@Override
	public String toString() {
		return String.format("ID: %s\ntitle: %s\nminAge: %d\n", movieID, title, minAge);
	}

	@Override
	public boolean equals(Object obj) {
		Movie m;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == this.getClass()) {
			m = (Movie) obj;
			return this.movieID.equals(m.movieID);
		} else
			return false;
	}

	@Override
	public int compareTo(Object o) {
		return this.title.compareTo(((Movie) o).title);
	}
}
