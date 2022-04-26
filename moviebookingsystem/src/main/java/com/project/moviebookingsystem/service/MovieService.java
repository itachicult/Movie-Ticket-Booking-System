package com.project.moviebookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.moviebookingsystem.model.Movie;
import com.project.moviebookingsystem.repository.MovieRepository;

@Service
@Transactional
public class MovieService {
	@Autowired
	private MovieRepository repo;
	
	public List<Movie> listAll(){
		return repo.findAll();
	}
	
	public void save(Movie movie) {
		repo.save(movie);
	}
	
	public Movie get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	public List<Movie> getByKeyword(String keyword){
		  keyword = keyword.toLowerCase();
		  return repo.findByKeyword(keyword);
	}
	public List<Movie> findByMovieName(String keyword){
		  keyword = keyword.toLowerCase();
		  return repo.findByKeyword(keyword);
	}
}
