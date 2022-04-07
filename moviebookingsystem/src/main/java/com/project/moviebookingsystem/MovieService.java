package com.project.moviebookingsystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
