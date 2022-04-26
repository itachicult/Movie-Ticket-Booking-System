package com.project.moviebookingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Movie {
	
	private long id;
	private String moviename;
	private String moviegenre;
	private String moviedescription;
	private float movierating;
	private float movieprice;
	private String movieimg;
	private String parentalrating;
	private long seats;

	public Movie() {
	}

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getMoviegenre() {
		return moviegenre;
	}

	public void setMoviegenre(String moviegenre) {
		this.moviegenre = moviegenre;
	}

	public String getMoviedescription() {
		return moviedescription;
	}

	public void setMoviedescription(String moviedescription) {
		this.moviedescription = moviedescription;
	}

	public float getMovierating() {
		return movierating;
	}

	public void setMovierating(float movierating) {
		this.movierating = movierating;
	}

	public float getMovieprice() {
		return movieprice;
	}

	public void setMovieprice(float movieprice) {
		this.movieprice = movieprice;
	}

	public String getMovieimg() {
		return movieimg;
	}

	public void setMovieimg(String movieimg) {
		this.movieimg = movieimg;
	}

	public String getParentalrating() {
		return parentalrating;
	}

	public void setParentalrating(String parentalrating) {
		this.parentalrating = parentalrating;
	}

	
	public long getSeats() {
		return seats;
	}


	public void setSeats(long seats) {
		this.seats = seats;
	}

	
}
