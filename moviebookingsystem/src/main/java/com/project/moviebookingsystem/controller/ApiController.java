package com.project.moviebookingsystem.controller;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.moviebookingsystem.model.CustomUserDetails;
import com.project.moviebookingsystem.model.Movie;
import com.project.moviebookingsystem.model.User;
import com.project.moviebookingsystem.model.movieticket;
import com.project.moviebookingsystem.repository.UserRepository;
import com.project.moviebookingsystem.service.MovieService;
import com.project.moviebookingsystem.service.MovieTicketService;

@Controller
public class ApiController {
	
	@Autowired
	private MovieService service;
	@Autowired
	private MovieTicketService ticketservice;
	@Autowired
    private UserRepository userRepo;
	
	//HttpServletRequest request;
	//String user=request.getUserPrincipal().getName();
	
	
	@RequestMapping("/")
	public String displayMainPage() {
		return "mainPage";
	}
	
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "SignUpPage";
    }
    
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_sucess";
    }
	
	@RequestMapping("/home")
	public String displayHomePage() {
		return "HomePage";
	}
	
	@RequestMapping("/movielist")
	public String viewAllMovies(Model model) {
		List<Movie> listMovies = service.listAll();
		model.addAttribute("listMovies", listMovies);
		return "movielist";
	}
	
	@RequestMapping("/search")
	public String viewMovieListPage(Movie movie, Model model, String keyword) {
		  if(keyword!=null) {
			   List<Movie> listMovies = service.getByKeyword(keyword);
			   model.addAttribute("listMovies", listMovies);
			  }
			  else {
			  List<Movie> listMovies = service.listAll();
			  model.addAttribute("listMovies", listMovies);
			  }
			  return "movielist";
	}
	
	@RequestMapping("/new")
	public String showNewMoviePage(Model model) {
	    Movie Movie = new Movie();
	    model.addAttribute("Movie",Movie);
	    return "new_movie";
	}
	
	@RequestMapping("/profile")
	public String showProfile(Principal principal,Model model,String keyword) {
		    List<movieticket> listmovietickets = ticketservice.getByKeyword(principal.getName());
		    model.addAttribute("listmovietickets", listmovietickets);
	        return "profile";
	}
	
	@RequestMapping("/book")
	public String showmovieticketbookpage(Model model) {
	    movieticket movieticket = new movieticket();
	    model.addAttribute("movieticket",movieticket);
	    return "movie_ticket";
	}
	
	@RequestMapping(value = "/saveticket", method = RequestMethod.POST)
	public String saveMovieTicket(@ModelAttribute("movieticket") movieticket movieticket) {
		ticketservice.save(movieticket);
	    return "redirect:/confirm";
	}
	
	@RequestMapping("/confirm")
	public String confirmation() {
		return "confirmation";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveMovie(@ModelAttribute("Movie") Movie movie) {
	    service.save(movie);
	    return "redirect:/movielist";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditMoviePage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_movie");
	    Movie movie = service.get(id);
	    mav.addObject("Movie", movie);
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteMovie(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
	@RequestMapping("/deleteticket/{id}")
	public String deleteMovieTicket(@PathVariable(name = "id") int id) {
		ticketservice.delete(id);
	    return "redirect:/profile";       
	}
}
