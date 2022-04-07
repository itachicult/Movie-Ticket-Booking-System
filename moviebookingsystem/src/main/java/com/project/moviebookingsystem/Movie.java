package com.project.moviebookingsystem;

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
	private String movierating;
	private String movieprice;
	private String movieimg;
	private float parentalrating;
	
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

	public String getMovierating() {
		return movierating;
	}

	public void setMovierating(String movierating) {
		this.movierating = movierating;
	}

	public String getMovieprice() {
		return movieprice;
	}

	public void setMovieprice(String movieprice) {
		this.movieprice = movieprice;
	}

	public String getMovieimg() {
		return movieimg;
	}

	public void setMovieimg(String movieimg) {
		this.movieimg = movieimg;
	}

	public float getParentalrating() {
		return parentalrating;
	}

	public void setParentalrating(float parentalrating) {
		this.parentalrating = parentalrating;
	}

	
}
