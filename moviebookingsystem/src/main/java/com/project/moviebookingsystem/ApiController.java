package com.project.moviebookingsystem;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {
	
	@Autowired
	private MovieService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Movie> listMovies = service.listAll();
		model.addAttribute("listMovies",listMovies);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewMoviePage(Model model) {
	    Movie Movie = new Movie();
	    model.addAttribute("Movie",Movie);
	    return "new_employee";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMovie(@ModelAttribute("Movie") Movie movie) {
	    service.save(movie);
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditMoviePage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_employee");
	    Movie movie = service.get(id);
	    mav.addObject("Movie", movie);
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteMovie(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
}
