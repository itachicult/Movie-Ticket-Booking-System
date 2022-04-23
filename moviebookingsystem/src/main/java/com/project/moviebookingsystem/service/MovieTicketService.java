package com.project.moviebookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.moviebookingsystem.model.movieticket;
import com.project.moviebookingsystem.repository.MovieTicketRepository;

@Service
@Transactional
public class MovieTicketService {
	@Autowired
	private MovieTicketRepository repo;
	
	public List<movieticket> listAll(){
		return repo.findAll();
	}
	
	public void save(movieticket movieticket) {
		repo.save(movieticket);
	}
	
	public movieticket get(long id) {
		return repo.findById(id).get();
	}
	public List<movieticket> getByKeyword(String keyword){
		  keyword = keyword.toLowerCase();
		  return repo.findByKeyword(keyword);
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
}
